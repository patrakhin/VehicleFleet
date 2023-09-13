package ru.patrakhin.VehicleFleet.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patrakhin.VehicleFleet.dto.CarBrandDTO;
import ru.patrakhin.VehicleFleet.dto.VehicleDriversDTO;
import ru.patrakhin.VehicleFleet.dto.VehicleGenerationDTO;
import ru.patrakhin.VehicleFleet.models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class VehicleGenerationService {
    private final EnterprisesService enterprisesService;
    private final VehicleService vehicleService;
    private final DriversService driversService;
    private final CarBrandService carBrandService;
    private final ModelMapper modelMapper;
    private final VehicleDriverService vehicleDriverService;

    @Autowired
    public VehicleGenerationService(EnterprisesService enterprisesService, VehicleService vehicleService, DriversService driversService, CarBrandService carBrandService,
                                    ModelMapper modelMapper, VehicleDriverService vehicleDriverService) {
        this.enterprisesService = enterprisesService;
        this.vehicleService = vehicleService;
        this.driversService = driversService;
        this.carBrandService = carBrandService;
        this.modelMapper = modelMapper;
        this.vehicleDriverService = vehicleDriverService;
    }

    public void generateVehicles(VehicleGenerationDTO request) {
        List<String> companyNames = request.getCompanyNames();
        int numberOfVehiclesPerCompany = request.getNumberOfVehiclesPerCompany();
        int activeDriverIndex = request.getActiveDriverIndex();

        // Для каждого предприятия в списке
        for (String companyName : companyNames) {
            Enterprises enterprise = enterprisesService.getEnterpriseByNameForAdmin(companyName);

            if (enterprise != null) {
                // Генерируем машины
                List<Vehicles> vehicles = generateRandomVehicle(enterprise, numberOfVehiclesPerCompany);
                vehicleService.addAllVehicles(vehicles);

                // Выбираем свободных водителей и распределяем их по машинкам в соответствии
                // с activeDriverIndex (какая по счету машинка должна быть с активным водителем)
                assignActiveDrivers(vehicles, activeDriverIndex);
            }
        }
    }

    private List<Vehicles>generateRandomVehicle(Enterprises enterprises, int numberOfVehicles) {
        // Получаем список всех доступных брендов машин из базы данных
        List<CarBrandDTO> carBrands = carBrandService.getAllCarBrands();

        List<Vehicles> vehicles = new ArrayList<>();
        for (int i = 0; i < numberOfVehicles; i++) {
            Vehicles vehicle = new Vehicles();
        // Выбираем случайный бренд из списка
        CarBrandDTO randomBrand = carBrands.get(new Random().nextInt(carBrands.size()));
        // Генерируем случайные параметры для машины
        vehicle.setEnterprises(enterprises);
        vehicle.setCarBrand(modelMapper.map(randomBrand, CarBrand.class));
        vehicle.setNumberVehicle(generateRandomNumber()); // Генерация номера машины
        vehicle.setPrice(generateRandomPrice()); // Генерация цены машины
        vehicle.setYearOfManufacture(generateRandomYear()); // Генерация года выпуска
        vehicle.setMileage(generateRandomMileage()); // Генерация пробега
        vehicle.setEquipmentType(generateRandomEquipmentType());// Генерация типа оборудования
        vehicles.add(vehicle);
        }
        return vehicles;
    }

    private String generateRandomNumber() {
        Random random = new Random();

        // Генерируем букву A-Z
        char letter1 = (char) (random.nextInt(26) + 'A');
        char letter2 = (char) (random.nextInt(26) + 'A');

        // Генерируем три цифры от 0 до 9
        int digits = random.nextInt(1000);

        // Форматируем номер в нужный вид
        return String.format("%c%d%d%d%c%c", letter1, digits / 100, (digits / 10) % 10, digits % 10, letter2, letter1);
    }

    private double generateRandomPrice() {
        double minPrice = 0.0; /* минимальная цена для бренда */
        double maxPrice = 10000.0; /* максимальная цена для бренда */
        Random random = new Random();
        return minPrice + (maxPrice - minPrice) * random.nextDouble();
    }

    private int generateRandomYear() {
        int minYear = 2012;
        int maxYear = 2022;
        Random random = new Random();
        return minYear + random.nextInt(maxYear - minYear + 1);
    }

    private int generateRandomMileage() {
        int maxMileage = 100000; /* максимальный пробег для бренда */
        Random random = new Random();
        return random.nextInt(maxMileage + 1);
    }

    private EquipmentType generateRandomEquipmentType() {
        EquipmentType[] values = EquipmentType.values();
        Random random = new Random();
        int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

/*    private void assignActiveDrivers(List<Vehicles> vehicles, int activeDriverIndex) {
        List<VehicleDriverService> vehicleDriverList = new ArrayList<>();

        for (Vehicles vehicle : vehicles) {
            //здесь нужен список водителей закрепленных за  конкретным предприятием
            List<Drivers> driversForVehicle = driversService.getAllDriversByEnterpriseId(vehicle.getEnterprises().getId());

            for (int i = 0; i < driversForVehicle.size(); i++) {
                Drivers driver = driversForVehicle.get(i);
                boolean isActive = (i == activeDriverIndex - 1);

                VehicleDriversDTO vehicleDriversDTO = new VehicleDriversDTO(vehicle, driver, isActive);
                //vehicleDriverList.add(modelMapper.map(vehicleDriversDTO, VehicleDriverService.class));
                vehicleDriverService.saveVehicleDriver(vehicleDriversDTO);
            }
        }
    }*/


    private void assignActiveDrivers(List<Vehicles> vehicles, int activeDriverIndex) {
        List<VehicleDrivers> vehicleDriversList = new ArrayList<>();
        Vehicles vehicles1 = vehicles.get(0);
        List<Drivers> driversForVehicle = driversService.getAllDriversByEnterpriseId(vehicles1.getEnterprises().getId());

        for (Vehicles vehicle : vehicles) {
            for (int i = 0; i < 2; i++) {
                if (!driversForVehicle.isEmpty()) {
                    Drivers driver = driversForVehicle.remove(0); // Получаем и удаляем первого водителя из списка
                    boolean isActive = (i + 1 == activeDriverIndex); // Проверяем, нужно ли сделать этого водителя активным

                    VehicleDriversDTO vehicleDriversDTO = new VehicleDriversDTO(vehicle, driver, isActive);
                    vehicleDriverService.saveVehicleDriver(vehicleDriversDTO);
                }
            }
        }
    }


}

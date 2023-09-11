package ru.patrakhin.VehicleFleet.dto;

import java.util.List;

public class VehicleGenerationDTO {
    private List<String> companyNames;
    private int numberOfVehiclesPerCompany; //количество генерируемых машинок для каждой компании
    private int activeDriverIndex; // каждая N-ая машинка с активным водителем

    public List<String> getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(List<String> companyNames) {
        this.companyNames = companyNames;
    }

    public int getNumberOfVehiclesPerCompany() {
        return numberOfVehiclesPerCompany;
    }

    public void setNumberOfVehiclesPerCompany(int numberOfVehiclesPerCompany) {
        this.numberOfVehiclesPerCompany = numberOfVehiclesPerCompany;
    }

    public int getActiveDriverIndex() {
        return activeDriverIndex;
    }

    public void setActiveDriverIndex(int activeDriverIndex) {
        this.activeDriverIndex = activeDriverIndex;
    }
}

package simulatordostavljanja.simulator;

import java.util.List;

public class ContractBean {

    private List<String> equipmentNames;
    private int quantity;
    private String exactDeliveryTime;
    private String hospitalName;
    private String hospitalAddress;

    public ContractBean(List<String> equipmentNames, int quantity, String exactDeliveryTime, String hospitalName, String hospitalAddress) {
        this.equipmentNames = equipmentNames;
        this.quantity = quantity;
        this.exactDeliveryTime = exactDeliveryTime;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
    }

    public List<String> getEquipmentNames() {
        return equipmentNames;
    }

    public void setEquipmentNames(List<String> equipmentNames) {
        this.equipmentNames = equipmentNames;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getExactDeliveryTime() {
        return exactDeliveryTime;
    }

    public void setExactDeliveryTime(String exactDeliveryTime) {
        this.exactDeliveryTime = exactDeliveryTime;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }


    @Override
    public String toString() {
        return "Contract{" +
                "equipmentNames=" + equipmentNames +
                ", quantity=" + quantity +
                ", exactDeliveryTime='" + exactDeliveryTime + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospitalAddress='" + hospitalAddress + '\'' +
                '}';
    }

}

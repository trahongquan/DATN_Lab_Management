package LabManagement.dtoExport;

public class EquipmentDtoExportWithLabName {
    private int id;
    private String name;
    private String serie;
    private String unit; /** Đơn vị tính */
    private String level;
    private String using; /** Ngày sử dụng */
    private String origin;
    private String labName;
    private String description;

    public EquipmentDtoExportWithLabName() {
    }

    public EquipmentDtoExportWithLabName(int id, String name, String serie, String unit, String level, String using, String origin, String labName, String description) {
        this.id = id;
        this.name = name;
        this.serie = serie;
        this.unit = unit;
        this.level = level;
        this.using = using;
        this.origin = origin;
        this.labName = labName;
        this.description = description;
    }

    @Override
    public String toString() {
        return "EquipmentDtoExportWithLabName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serie='" + serie + '\'' +
                ", unit='" + unit + '\'' +
                ", level='" + level + '\'' +
                ", using='" + using + '\'' +
                ", origin='" + origin + '\'' +
                ", labName='" + labName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUsing() {
        return using;
    }

    public void setUsing(String using) {
        this.using = using;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

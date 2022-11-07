package edu.smu;

import java.util.List;
import java.util.Objects;

public class SchoolPlayground extends Playground {
    private String schoolType;
    private String schoolName;

//    private List<String> equipment;


    public SchoolPlayground() {
        super(null, "generic park", 0);
    }

    public SchoolPlayground(String schoolType, String schoolName, List<String> equipment, String parkName) {
        super(equipment, parkName, 1);
//        this.setAcres(1);
        this.schoolType = schoolType;
        this.schoolName = schoolName;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public boolean equals(Object o) {
//        return true;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolPlayground that = (SchoolPlayground) o;
        return Objects.equals(schoolType, that.schoolType) && Objects.equals(schoolName, that.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schoolType, schoolName);
    }

    @Override
    public String toString() {
        return "SchoolPlayground{" +
                "schoolType='" + schoolType + '\'' +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }


}

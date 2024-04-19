package LabManagement.ClassSuport;

import java.util.ArrayList;
import java.util.List;

import static LabManagement.ClassSuport.ToList.containsSpace;

public class EquiDesFix {
    public static List<String> seriFixed;
    public static List<DateAndDesEqui> dateAndDesEquis;

    public void seriFixed(String stringSeriFixed){
        seriFixed = new ToList().StringToList(stringSeriFixed);
    }

//    public void Description2DateAndDesEquis(String description){
//
//        // Xóa ký tự "[" và "]" từ chuỗi
//        String cleanedInput = description.replace("[", "").replace("]", "");
//
//        // Tách các số bằng dấu phẩy
//        String[] seris = cleanedInput.split(",\\s*");
//
//        // Chuyển các số từ kiểu String sang kiểu Integer và thêm vào danh sách
//        for (String seri : seris) {
//            seri = seri.trim();
//            if(!containsSpace(seri) && !seri.equals("")){
//                result.add(seri);
//            }
//        }
//    }
}

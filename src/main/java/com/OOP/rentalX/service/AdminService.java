package com.OOP.rentalX.service;

import com.OOP.rentalX.model.Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminService {

    private static final String FILE_PATH = "src/main/resources/admins.txt";

    public void register(Admin admin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(toLine(admin));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Admin login(String id, String password) {
        List<Admin> list = getAll();
        for (Admin a : list) {
            if (a.getAdminId().equals(id) && a.getPassword().equals(password)) {
                return a;
            }
        }
        return null;
    }

    private String toLine(Admin a) {
        return a.getAdminId() + "," + a.getName() + "," + a.getEmail() + "," + a.getPassword();
    }

    private List<Admin> getAll() {
        List<Admin> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",", 4);
                list.add(new Admin(p[0], p[1], p[2], p[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

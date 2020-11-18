package com.example.SpringProject.Services;

import com.example.SpringProject.models.Employee;
import com.example.SpringProject.models.Part;
import com.example.SpringProject.models.Works;
import com.example.SpringProject.repositories.WorksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorksService {

    @Autowired
    WorksRepository worksRepository;

    public List<Works> getWorksByEmployee(Employee employee) {
        return worksRepository.findByEmployee(employee);
    }
    public void savework(Works work) {
        worksRepository.save(work);
    }
    public Works getWorkById(int works_id) {
        Optional<Works> optional = worksRepository.findById(works_id);
        if (optional.isPresent()) return optional.get();
        else throw new RuntimeException("No work with id = " + works_id);
    }
    public void deleteByWork(Works work) {
        worksRepository.delete(work);
    }
    public boolean checkByDate(Employee employee, Part part, int day, int month, int year) {
        Optional<Works> optional = worksRepository.findByEmployeeAndPartAndDayAndMonthAndYear(employee, part, day, month, year);
        return optional.isPresent();
    }
    public Works getByDate(Employee employee, Part part, int day, int month, int year) {
        Optional<Works> optional = worksRepository.findByEmployeeAndPartAndDayAndMonthAndYear(employee, part, day, month, year);
        if (optional.isPresent()) return optional.get();
        else throw new RuntimeException("No work exists");
    }
}

package liv2train.service;

import liv2train.entity.TrainingCenter;
import liv2train.repository.TrainingCenterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrainingCenterService {

    private final TrainingCenterRepository trainingCenterRepository;

    @Autowired
    public TrainingCenterService(TrainingCenterRepository trainingCenterRepository) {
        this.trainingCenterRepository = trainingCenterRepository;
    }

    public TrainingCenter createTrainingCenter(TrainingCenter input) {
        TrainingCenter trainingCenter = new TrainingCenter(
                generateCenterCode(),
                input.getCenterName(),
                input.getAddress(),
                input.getStudentCapacity(),
                input.getCourses(),
                input.getContactEmail(),
                input.getContactPhone(),
                new Date()
        );
        return trainingCenterRepository.save(trainingCenter);
    }

    public List<TrainingCenter> getTrainingCenters(
            String centerCode,
            String centerName,
            String city,
            String state,
            String pinCode,
            String course
    ) {
        List<TrainingCenter> trainingCenters = trainingCenterRepository.findAll();
        if (centerCode != null) {
            trainingCenters = trainingCenters
                    .stream()
                    .filter(t -> t.getCenterCode().equals(centerCode))
                    .collect(Collectors.toList());
        }
        if (centerName != null) {
            trainingCenters = trainingCenters
                    .stream()
                    .filter(t -> t.getCenterName().equals(centerName))
                    .collect(Collectors.toList());
        }
        if (city != null) {
            trainingCenters = trainingCenters
                    .stream()
                    .filter(t -> t.getAddress().getCity().equals(city))
                    .collect(Collectors.toList());
        }
        if (state != null) {
            trainingCenters = trainingCenters
                    .stream()
                    .filter(t -> t.getAddress().getState().equals(state))
                    .collect(Collectors.toList());
        }
        if (pinCode != null) {
            trainingCenters = trainingCenters
                    .stream()
                    .filter(t -> t.getAddress().getPinCode().equals(pinCode))
                    .collect(Collectors.toList());
        }
        if (course != null) {
            trainingCenters = trainingCenters
                    .stream()
                    .filter(t -> t.getCourses().contains(course))
                    .collect(Collectors.toList());
        }
        return trainingCenters;
    }

    public TrainingCenter getTrainingCenterByCenterCode(String centerCode) {
        return trainingCenterRepository
                .findById(centerCode)
                .orElse(null);
    }

    public String generateCenterCode() {
        String uuid;
        while (true) {
            uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "").substring(0, 12);
            TrainingCenter trainingCenter = getTrainingCenterByCenterCode(uuid);
            if (trainingCenter == null) {
                break;
            }
        }
        return uuid;
    }
}

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

/**
 * Service class for training center endpoints, containing the business logic
 */
@Service
@Slf4j
public class TrainingCenterService {

    private final TrainingCenterRepository trainingCenterRepository;

    @Autowired
    public TrainingCenterService(TrainingCenterRepository trainingCenterRepository) {
        this.trainingCenterRepository = trainingCenterRepository;
    }

    /**
     * Function to create a new training center
     * @param trainingCenter contains the details passed in the request
     * @return the newly created training center
     */
    public TrainingCenter createTrainingCenter(TrainingCenter trainingCenter) {
        TrainingCenter newTrainingCenter = new TrainingCenter(
                generateCenterCode(),
                trainingCenter.getCenterName(),
                trainingCenter.getAddress(),
                trainingCenter.getStudentCapacity(),
                trainingCenter.getCourses(),
                trainingCenter.getContactEmail(),
                trainingCenter.getContactPhone(),
                new Date()
        );
        return trainingCenterRepository.save(newTrainingCenter);
    }

    /**
     * Method to get the training centers based on the filters passed
     * via request params
     * @param centerCode the center code (PK) of the training center
     * @param centerName the name of the center
     * @param city city in which the center is present
     * @param state  state in which the center is present
     * @param pinCode pinCode of the center area
     * @param course particular course offered by any center
     * @return list of training centers
     */
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

    /**
     * Method to get training center details from the center code
     * @param centerCode the center code
     * @return traning center
     */
    public TrainingCenter getTrainingCenterByCenterCode(String centerCode) {
        return trainingCenterRepository
                .findById(centerCode)
                .orElse(null);
    }

    /**
     * Function to create a unique 12 character alphanumeric string (center code)
     * @return the 12 character string
     */
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

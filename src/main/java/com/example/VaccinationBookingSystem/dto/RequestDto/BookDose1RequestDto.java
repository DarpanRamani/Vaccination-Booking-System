package com.example.VaccinationBookingSystem.dto.RequestDto;

import com.example.VaccinationBookingSystem.Enum.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDose1RequestDto {
    int personId;
    DoseType doseType;
}

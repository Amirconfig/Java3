package ca.sheridancollege.khanmoam.beans;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Course {

    @NonNull
    private Long id;
    private String prefix;
    private int code;
    private String name;
}

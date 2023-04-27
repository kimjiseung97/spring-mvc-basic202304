package com.example.mvc.etc;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Actor {

    private String actorName;
    private int actorAge;
    private boolean hasPhone;
}

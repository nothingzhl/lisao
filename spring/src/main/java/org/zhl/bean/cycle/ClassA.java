package org.zhl.bean.cycle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class ClassA {

    @NonNull
    private ClassB classB;
}

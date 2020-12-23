package org.zhl.attach;

import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

/**
 * Attach 相关Api的使用
 */
public class AttachTest {

    public static void main(String[] args) {
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        vms.forEach(vm->{
            System.out.println(String.format("pid:%s->display:%s", vm.id(), vm.displayName()));
        });
    }

}

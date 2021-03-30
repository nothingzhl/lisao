package org.zhl.attach;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

/**
 * Attach 相关Api的使用
 */
public class AttachTest {

    public static void main(final String[] args) {
        final List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        vms.forEach(vm -> System.out.println(String.format("pid:%s->display:%s", vm.id(), vm.displayName())));
    }

}

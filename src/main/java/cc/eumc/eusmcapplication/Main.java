package cc.eumc.eusmcapplication;

import cc.eumc.eusmcapplication.utils.SendEmail;

public class Main {
    public static void main(String[] args) {
        SendEmail.send(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
    }
}

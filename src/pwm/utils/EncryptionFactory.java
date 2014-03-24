package pwm.utils;

import pwm.Assets;
import pwm.PWMException;
import pwm.engine.algorithms.encryptionalgorithms.*;

public class EncryptionFactory {

    public static EncryptionAlgorithm getEncryption() {
        Assets ass = Assets.get();

        String hashAlg = ass.getSetting("profile.encryption");
        try {

            switch (hashAlg) {
            case "AES":
                return new AES128Encryption(HashFactory.getHash());

            case "TEST":
                return new MockEncryption(HashFactory.getHash());
            default:
                return new AES128Encryption(HashFactory.getHash());
            }

        } catch (PWMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
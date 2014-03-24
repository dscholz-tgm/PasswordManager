package pwm.utils;

import pwm.Assets;
import pwm.engine.algorithms.hashingalgorithms.*;

public class HashFactory {

    public static HashingAlgorithm getHash() {
        Assets ass = Assets.get();

        String hashAlg = ass.getSetting("profile.hash");

        switch (hashAlg) {
        case "SHA-1":
            return new SHA1Hash();
        case "SHA-256":
            return new SHA256Hash();
        case "MD5":
            return new MD5Hash();
        case "PWMHash":
            return new PWMHash();
        default:
            return new PWMHash();
        }
    }
}

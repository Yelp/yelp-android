package com.yelp.clientlib.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationTestUtils {

    /**
     * Serialize an object into a byte array. The object has to implement {@link Serializable} interface.
     *
     * @param object Object to be serialized.
     * @return Byte array serialized from the object.
     */
    public static <T extends Serializable> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * Deserialize a byte array into an object. The object has to implement {@link Serializable} interface.
     *
     * @param bytes Byte array to be deserialized.
     * @param clazz Class type the object should be deserialized into.
     * @return Object deserialized from the byte array.
     */
    public static <T extends Serializable> T deserialize(byte[] bytes, Class<T> clazz)
            throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();

        return clazz.cast(object);
    }
}

package com.fasterxml.jackson.datatype.hibernate4;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.type.*;

public class HibernateSerializerModifier
    extends BeanSerializerModifier
{
    protected final boolean _forceLoading;
    protected final boolean _forceLoadingElementCollection;
    
    public HibernateSerializerModifier(boolean forceLoading, boolean forceLoadingElementCollection) {
        _forceLoading = forceLoading;
        _forceLoadingElementCollection = forceLoadingElementCollection;
    }
    
    /*
    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config,
            BeanDescription beanDesc, JsonSerializer<?> serializer) {
        return serializer;
    }
    */

    @Override
    public JsonSerializer<?> modifyCollectionSerializer(SerializationConfig config,
            CollectionType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        return new PersistentCollectionSerializer(_forceLoading, _forceLoadingElementCollection, serializer);
    }

    @Override
    public JsonSerializer<?> modifyMapSerializer(SerializationConfig config,
            MapType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        return new PersistentCollectionSerializer(_forceLoading, _forceLoadingElementCollection, serializer);
    }
}

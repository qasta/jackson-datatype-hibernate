package com.fasterxml.jackson.datatype.hibernate4;

import org.hibernate.engine.spi.Mapping;
import org.hibernate.proxy.HibernateProxy;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.*;

public class HibernateSerializers extends Serializers.Base
{
    protected final boolean _forceLoading;
    protected final boolean _serializeIdentifiers;
    protected final Mapping _mapping;
    protected final boolean _forceLoadingForElementCollection;

    public HibernateSerializers(boolean forceLoading)
    {
        this(forceLoading, false, forceLoading, null);
    }

    public HibernateSerializers(boolean forceLoading, boolean serializeIdentifiers)
    {
        this(forceLoading, serializeIdentifiers, forceLoading, null);
    }

    public HibernateSerializers(boolean forceLoading, boolean forceLoadingElementCollection, boolean serializeIdentifiers, Mapping mapping)
    {
        _forceLoading = forceLoading;
        _serializeIdentifiers = serializeIdentifiers;
        _forceLoadingForElementCollection = forceLoadingElementCollection;
        _mapping = mapping;
    }

    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config,
            JavaType type, BeanDescription beanDesc)
    {
        Class<?> raw = type.getRawClass();
        if (HibernateProxy.class.isAssignableFrom(raw)) {
            return new HibernateProxySerializer(_forceLoading, _forceLoadingForElementCollection, _serializeIdentifiers, _mapping);
        }
        return null;
    }
}

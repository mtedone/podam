package uk.co.jemos.podam.common;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class AttributeStrategyStore {

    private AttributeStrategyStore(){}

    private static final Table<Class<?>, String, AttributeStrategy<?>> attributeStrategyTable = HashBasedTable.create();


    public static void setAttributeStrategyForField(Class<?> clazz, String fieldName, AttributeStrategy<?> attributeStrategy) {
        attributeStrategyTable.put(clazz, fieldName, attributeStrategy);
    }

    public static void deleteAttributeStrategyForField(Class<?> clazz, String fieldName, AttributeStrategy<?> attributeStrategy) {
        attributeStrategyTable.remove(clazz, fieldName);
    }

    public static Table<Class<?>, String, AttributeStrategy<?>> getStore() {
        return attributeStrategyTable;
    }

    public static AttributeStrategy<?> geAttributeStrategy(Class<?> clazz, String fieldName) {
        return attributeStrategyTable.get(clazz, fieldName);
    }
}

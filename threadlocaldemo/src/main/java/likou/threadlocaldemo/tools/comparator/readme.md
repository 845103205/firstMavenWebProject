# 两对象差量比较 接口用法
## 1、属性、值不翻译成中文调用方法
### 参数解释
    configCollectDataLabel:改变前数据对象
    configCollectDataLabel:改变后数据对象
### 调用方法
    // 新增方法调用[单对象转字符串]
    String result =  BeanComparator.getCompareResult(configCollectDataLabel);
    
    // 修改方法调用    
    String result =  BeanComparator.getCompareResult(collectDataLabel,configCollectDataLabel);

## 2、属性、值翻译成中文调用方法
### 参数解释
    configCollectDataLabel:改变前数据对象
    configCollectDataLabel:改变后数据对象
    propertyTranslationMap:属性和属性中文对应关系集合
    dataDictionaryMap: 属性和属性字典关系集合
### 调用方法    
    //修改方法调用    
    String result =  BeanComparator.getCompareResult(configCollectDataLabel, configCollectDataLabel, propertyTranslationMap,dataDictionaryMap);
    
    // 新增方法调用[单对象转字符串]
    String result =  BeanComparator.getCompareResult(configCollectDataLabel, propertyTranslationMap,dataDictionaryMap);

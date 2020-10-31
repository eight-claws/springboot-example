# Getting Started

### features
- 通过@SailResponse来处理返回值

    原理：
    装饰下@Responsebody的RequestResponseBodyMethodProcessor，
    也就是在把返回值交给他之前先调用SailResponseReturnValueHandler，把返回值包装成RestResult
    然后再由RequestResponseBodyMethodProcessor把返回值处理成json


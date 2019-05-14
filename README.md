# InactiveModel::Validations

`inactivemodel-validations` is a (not-so-serious) attempt to replicate some of the functionality of 
Ruby on Rails' ActiveModel validations in Java. The idea is to throw a bunch of annotations into your model
and out comes a validation report.

The possible validations go beyond just checking a string for its length or making sure that a 
numeric value falls within a certain range (though that's also possible) by allowing to register
a custom validator on a field-level, which has full access to the entire model. Through this, it
is also possible to validate things like relationships between fields, like for example 'field 
`A` is only set if field `B` is **not** set'.

Note: This library has never been used in a real project and was developed for practice during 
my free time. 


## Example

Validators generally consist of two parts: an annotation and an implementation. As an example, let's take
the EachValidator `@NonNull`:


```java
@EachValidator
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NonNull {

    Class<? extends AbstractEachValidator> value() default NonNullValidator.class;
}
```
The first important part is the `@EachValidator` annotation, which has to be set in order for the 
record validator to recognize it as such. For context aware validators, the `@ClassValidator` annotation
has to be set instead (more on those later). Without either of those, the validator will be ignored. 
Regarding the `@Target` and `@Retention`, there are no other options besides `ElementType.FIELD` and 
`RetentionPolicy.RUNTIME` for now.

For the annotation itself, it is mandatory to use the `value()` for a representation of the
implementation class. The preferred way for EachValidators is to default to the validator implementation, 
as can be seen above, but it is also possible to pass the class as an argument, of course.

Regarding the implementation of the validation it is necessary to extend the appropriate abstract
validator. In our case, the `AbstractEachValidator`.

```java
public class NonNullValidator extends AbstractEachValidator {

    public void validate() {
        if (value == null) {
            validationReport.addError("Value of '{}' must not be null.", fieldName);
        }
    }
}
```
This will make things like the current `fieldName` and `value` but also a reference to the validated `record`
and a `validationReport` available for use. For EachValidators, the record is only of type `Object` and in fact,
is rarely used, as EachValidators focus only on individual fields.

When in comes to ClassValidators, however, the record is of type `T` and has to be specified with the implementation
of the validator. This is necessary, because ClassValidators (or context aware validators) are used to check fields
in the context of other fields, like checking if flags within an object are set correctly. 

```java
public class UserHasDriversLicenseFlagValidator extends AbstractClassValidator<TestUserRecord> {
    
    public void validate() {
        // `value` holds the value of a boolean flag that is set according
        // to the presence of a drivers license object.
        if (value && record.getDriversLicense() == null) {
            // ...
        }
    }
}
```




# TODO'S
- Maybe rename ClassValidator to something else to reduce confusion, as
class validators also must only target fields (ContextAwareValidator?) ALSO ADJUST README
- Validations which cannot validate null fields need some special treatment. Currently it is
necessary to explicitly return from Validator#validate() if the checked value is null. To reduce
this boilerplate, it should be handled by the lib. Probably some implicit opt-out skip.
- improve validation error messages. Add some default text or structure (like in Ruby)
- decide what to do with exceptions that cause the program to exit
- Write tests

## Maybe TODO
- Add annotation type that delegates its values to another EachValidator
-- eg `@Uuid` validator delegates uuid-regex to `@UsingRegex` validator
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

```
Soon
```




# TODO'S
- Write tests
- Write readme
- Maybe rename ClassValidator to something else to reduce confusion, as
class validators also must only target fields (ContextAwareValidator?)
- Validators have to know about one another. Eg, they need to know if a field can be null or not. Or assume
each field is nullable and add a non-null validator with higher presendence. For now, validations for specific
values just skip when field is null which should be fine, since the validations *cannot* happen either way. So
if the field must not be null, it has to be checked beforehand. But maybe build 'skip if null' into the lib, to
reduce boilerplate.
- Finish aforementioned TODO's

## Maybe TODO
- Add annotation type that delegates its values to another EachValidator
-- eg `@Uuid` validator delegates uuid-regex to `@UsingRegex` validator
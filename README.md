# InactiveModel::Validations

`inactivemodel-validations` is a (not-so-serious) attempt to replicate some of the functionality of 
Ruby on Rails' ActiveModel validations in Java. The idea is to throw a bunch of annotations into your model
and out comes a validation report.

The possible validations go beyond just checking a string for its length or making sure that a 
numeric value falls within a certain range--though that's also possible--by allowing to register
a custom validator on a field-level, which has full access to the entire model. Through this, it
is also possible to validate things like relation ships between fields, like for example 'field 
`A` is only set if field `B` is **not** set'.

Note: This library has never been used in a real project and was developed for practice during 
my free time. 


## Example

```
Soon
```




# TODO'S
- Comment methods that are used to implement new validators
  - The `value()` method of Validator annotations **must only be** the class
that is doing the validation. Specific validation parameters (eg min or
max length for length validations) have to be specified separately. (Document!)

- Write tests
- Write readme
- Finish aforementioned TODO's

## Maybe TODO
- Add annotation type that delegates its values to another EachValidator
-- eg `@Uuid` validator delegates uuid-regex to `@UsingRegex` validator
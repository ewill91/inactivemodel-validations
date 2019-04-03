# InactiveModel::Validations
because i like Ruby on Rails' ActiveModel::Validations

The `value()` method of Validator annotations **must only be** the class
that is doing the validation. Specific validation parameters (eg min or
max length for length validations) have to be specified separately.

# TODO'S
- Cleanup ReflectionUtil (ie properly handle errors)
- Write tests
- Write readme
- Finish aforementioned TODO's

## Maybe TODO
- Add annotation type that delegates its values to another EachValidator
-- eg `@Uuid` validator delegates uuid-regex to `@UsingRegex` validator
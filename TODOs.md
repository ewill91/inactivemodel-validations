
# TODO'S
- Maybe rename ClassValidator to something else to reduce confusion, as
class validators also must only target fields (ContextAwareValidator?) ALSO ADJUST README
- Validations which cannot validate null fields need some special treatment. Currently it is
necessary to explicitly return from Validator#validate() if the checked value is null. To reduce
this boilerplate, it should be handled by the lib. Probably some implicit opt-out skip.
- if validated field is not a primitive, the `value` maybe cannot be of type `Object`?
- improve validation error messages. Add some default text or structure (like in Ruby)
- decide what to do with exceptions that cause the program to exit
- Write tests

## Maybe TODO
- Add annotation type that delegates its values to another EachValidator
-- eg `@Uuid` validator delegates uuid-regex to `@UsingRegex` validator
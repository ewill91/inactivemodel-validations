# InactiveModel::Validations
because i like Ruby on Rails' approach to model-validations



The `value()` method of Validator annotations **must only be** the class
that is doing the validation. Specific validation parameters (eg min or
max length for length validations) have to be specified separately.
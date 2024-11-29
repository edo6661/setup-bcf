package com.example.slicingbcf.domain.validator

data class FieldValidation(
  val isValid : Boolean,
  val errorMessage : String?
)

fun validateField(field : String, errorMessage : String) : FieldValidation {
  return if (field.isBlank()) {
    FieldValidation(isValid = false, errorMessage = errorMessage)
  } else {
    FieldValidation(isValid = true, errorMessage = null)
  }
}

fun validateEmail(email : String) : FieldValidation {
  return if (email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
    FieldValidation(isValid = true, errorMessage = null)
  } else {
    FieldValidation(isValid = false, errorMessage = "Email tidak valid")
  }
}

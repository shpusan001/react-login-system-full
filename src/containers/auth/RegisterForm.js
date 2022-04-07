import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import AuthForm from "../../components/auth/AuthForm";
import { changeField, initializeForm } from "../../modules/auth";

const RegisterForm = () => {
  const dispatch = useDispatch();
  const { form } = useSelector(({ auth }) => ({
    form: auth.register,
  }));

  //인풋 변경 이벤트 핸들러
  const onChange = (e) => {
    const { value, name } = e.target;
    dispatch(
      changeField({
        form: "register",
        key: name,
        value,
      }),
    );
  };

  //폼 이벤트 등록 핸들러
  const onSubmit = (e) => {
    e.preventDefault();
  };

  useEffect(() => {
    dispatch(initializeForm("register"));
  }, [dispatch]);

  return (
    <AuthForm
      type="register"
      form={form}
      onChange={onChange}
      onSubmit={onSubmit}
    />
  );
};

export default RegisterForm;
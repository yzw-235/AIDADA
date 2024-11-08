<template>
  <div id="userRegisterPage">
    <a-form
      ref="formRef"
      :rules="rules"
      :model="form"
      :style="{ width: '480px', margin: '0 auto' }"
      @submit="handleSubmit"
    >
      <a-form-item field="userAccount" label="用户名" validate-trigger="blur">
        <a-input v-model="form.userAccount" placeholder="请输入用户名..." />
      </a-form-item>
      <a-form-item field="userPassword" label="密码" validate-trigger="blur">
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码..."
        />
      </a-form-item>
      <a-form-item
        field="checkPassword"
        label="确认密码"
        validate-trigger="blur"
      >
        <a-input-password
          v-model="form.checkPassword"
          placeholder="确认密码..."
        />
      </a-form-item>
      <a-form-item>
        <div class="btnDiv">
          <a-button type="primary" :long="false" html-type="submit">
            &emsp; 注册 &cirscir; &ensp;
          </a-button>
          <a href="/user/login">已注册？跳转登录</a>
        </div>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { userRegisterUsingPost } from "@/api/userController";
import { Message } from "@arco-design/web-vue";
import { reactive } from "vue";
import API from "@/api";
import { useRouter } from "vue-router";

const router = useRouter();
const handleSubmit = async ({ values, errors }) => {
  if (errors !== undefined) {
    return false;
  }
  const res = await userRegisterUsingPost(values);
  if (res.data.code === 0) {
    Message.success("注册成功");
    router.push({
      path: "/user/login",
      replace: true,
    });
  } else {
    Message.error("注册失败：" + res.data.message);
  }
};

const form = reactive({
  userAccount: "",
  userPassword: "",
  checkPassword: "",
} as API.UserRegisterRequest);

const rules = {
  userAccount: [
    {
      required: true,
      message: "请输入用户名",
    },
  ],
  userPassword: [
    {
      required: true,
      message: "请输入用户密码",
    },
  ],
  checkPassword: [
    {
      required: true,
      message: "请输入确认密码",
    },
    {
      validator: (value, cb) => {
        if (value !== form.userPassword) {
          cb("两次输入密码不一致");
        } else {
          cb();
        }
      },
    },
  ],
};
</script>
<style scoped>
#userRegisterPage {
}
#userRegisterPage .btnDiv {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}
</style>

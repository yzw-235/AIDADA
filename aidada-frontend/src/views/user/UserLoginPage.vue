<template>
  <div id="userLoginPage">
    <h2 style="margin-bottom: 16px">用户登录</h2>
    <a-form
      ref="formRef"
      :rules="rules"
      :model="form"
      :style="{ width: '480px', margin: '0 auto' }"
      @submit="handleSubmit"
    >
      <a-form-item
        :hide-asterisk="true"
        field="userAccount"
        label="账号"
        validate-trigger="blur"
      >
        <a-input v-model="form.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item
        :hide-asterisk="true"
        field="userPassword"
        label="密码"
        validate-trigger="blur"
      >
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </a-form-item>
      <a-form-item>
        <div class="btnDiv">
          <a-button type="primary" html-type="submit">
            &emsp;登录 &emsp;</a-button
          >
          <a href="/user/register">新用户注册</a>
        </div>
      </a-form-item>
    </a-form>
  </div>
</template>
<script setup lang="ts">
import { reactive } from "vue";
import API from "@/api";
import { userLoginUsingPost } from "@/api/userController";
import { useUserStore } from "@/store/userStore";
import { Message } from "@arco-design/web-vue";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const router = useRouter();
const handleSubmit = async ({ values, errors }) => {
  if (errors !== undefined) {
    return false;
  }
  const res = await userLoginUsingPost(values);
  if (res.data.code === 0) {
    await userStore.fetchLoginUser();
    Message.success("登录成功");
    router.push({
      path: "/",
      replace: true,
    });
  } else {
    console.log(res.data);

    Message.error("登录失败" + res.data.message);
  }
};

const form = reactive({
  userAccount: "",
  userPassword: "",
} as API.UserLoginRequest);

const rules = {
  userAccount: [
    {
      required: true,
      message: "请输入账号",
    },
  ],
  userPassword: [
    {
      required: true,
      message: "请输入密码",
    },
  ],
};
</script>
<style scoped>
#userLoginPage {
}
#userLoginPage .btnDiv {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}
</style>

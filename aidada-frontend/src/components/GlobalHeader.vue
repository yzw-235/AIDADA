<template>
  <a-row id="globalHeader" align="center" :wrap="false">
    <a-col flex="auto">
      <div>
        <a-menu
          mode="horizontal"
          :selected-keys="selectKeys"
          @menu-item-click="doMenuClick"
        >
          <a-menu-item
            key="0"
            :style="{ padding: 0, marginRight: '38px' }"
            disabled
          >
            <div class="titleBar">
              <img class="logo" src="../assets/logo.png" />
              <div class="title">AI答答</div>
            </div>
          </a-menu-item>
          <a-menu-item v-for="item in visibleMenu" :key="item.path">
            {{ item.name }}
          </a-menu-item>
        </a-menu>
      </div>
    </a-col>
    <a-col flex="100px">
      <div v-if="loginUserStore.loginUser.id">
        {{ loginUserStore.loginUser.userName ?? "无名" }}
      </div>
      <div v-else>
        <a-button type="primary" href="/user/login">登录</a-button>
      </div>
    </a-col>
  </a-row>
</template>
<script setup lang="ts">
import { useRouter } from "vue-router";
import { routes } from "../router/routes";
import { computed, ref } from "vue";
import { useUserStore } from "@/store/userStore";
import checkAccess from "@/access/checkAccess";
const loginUserStore = useUserStore();
const router = useRouter();
const selectKeys = ref(["/"]);
router.afterEach((to, from, failure) => {
  selectKeys.value = [to.path];
});
const visibleMenu = computed(() => {
  return routes.filter((e) => {
    if (e.meta?.hideInMenu) {
      return false;
    }
    if (checkAccess(loginUserStore.loginUser, e.meta?.access as string)) {
      return true;
    }
    return false;
  });
});
function doMenuClick(key: string) {
  router.push({
    path: key,
  });
}
</script>
<style scoped>
#globalHeader {
}
.titleBar {
  display: flex;
  align-items: center;
}
.title {
  margin-left: 16px;
  color: #000;
}
.logo {
  height: 48px;
}
</style>

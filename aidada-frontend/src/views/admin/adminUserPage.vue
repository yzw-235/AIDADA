<template>
  <div id="adminUserPage">
    <a-form
      :model="searchForm"
      label-align="left"
      layout="inline"
      class="searchForm"
    >
      <a-form-item field="userName" label="用户名">
        <a-input
          v-model="searchForm.userName"
          placeholder="请输入用户名"
        ></a-input>
      </a-form-item>
      <a-form-item>
        <a-space>
          <a-button type="primary" @click="doSearch">搜索</a-button>
        </a-space>
      </a-form-item>
    </a-form>
    <a-table
      :columns="columns"
      :data="dataList"
      :pagination="{
        showTotal: true,
        pageSize: searchParams.pageSize,
        current: searchParams.current,
        total,
      }"
      @page-change="onPageChange"
    >
      <template #userAvatar="{ record }">
        <a-image width="64" :src="record.userAvatar"></a-image>
      </template>
      <template #optional="{ record }">
        <a-space>
          <a-button type="primary" status="warning" @click="onDelete(record)"
            >删除</a-button
          >
        </a-space>
      </template>
    </a-table>
  </div>
</template>

<script setup lang="ts">
import {
  deleteUserUsingPost,
  listUserByPageUsingPost,
} from "@/api/userController";
import { reactive, ref, watchEffect } from "vue";
import API from "@/api";
import { Message } from "@arco-design/web-vue";

const initSearchParams = {
  current: 1,
  pageSize: 10,
  userName: undefined,
};
const searchParams = ref({
  ...initSearchParams,
} as API.UserQueryRequest);
const searchForm = ref<API.UserQueryRequest>({});

const dataList = ref<API.User[]>([]);
const total = ref<number>(0);
/**
 * 加载列表数据
 */
async function loadData() {
  const res = await listUserByPageUsingPost(searchParams.value);
  if (res.data.code === 0) {
    dataList.value = res.data.data?.records || [];
    total.value = res.data.data?.total || 0;
  } else {
    Message.error("查询错误:" + res.data.message);
  }
}
/**
 * 处理查询
 */
function doSearch() {
  searchParams.value = {
    ...initSearchParams,
    ...searchForm.value,
  };
}
/**
 * 处理分页改变
 */
function onPageChange(page: number) {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
}
/**
 * 处理删除
 */
async function onDelete(record: API.User) {
  console.log(record);
  if (!record.id) {
    return false;
  }
  confirm();
  const res = await deleteUserUsingPost({
    id: record.id,
  });
  if (res.data.code === 0) {
    Message.success("删除成功");
    loadData();
  } else {
    Message.error("删除失败:" + res.data.message);
  }
}
watchEffect(() => {
  loadData();
});
const columns = [
  {
    title: "ID",
    dataIndex: "id",
  },
  {
    title: "账号",
    dataIndex: "userAccount",
  },
  {
    title: "用户名",
    dataIndex: "userName",
  },
  {
    title: "用户头像",
    dataIndex: "userAvatar",
    slotName: "userAvatar",
  },
  {
    title: "个签",
    dataIndex: "userProfile",
  },
  {
    title: "角色",
    dataIndex: "userRole",
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
  },
  {
    title: "更新时间",
    dataIndex: "updateTime",
  },
  {
    title: "操作",
    slotName: "optional",
  },
];
const data = reactive([
  {
    key: "1",
    name: "Jane Doe",
    salary: 23000,
    address: "32 Park Road, London",
    email: "jane.doe@example.com",
  },
  {
    key: "2",
    name: "Alisa Ross",
    salary: 25000,
    address: "35 Park Road, London",
    email: "alisa.ross@example.com",
  },
  {
    key: "3",
    name: "Kevin Sandra",
    salary: 22000,
    address: "31 Park Road, London",
    email: "kevin.sandra@example.com",
  },
  {
    key: "4",
    name: "Ed Hellen",
    salary: 17000,
    address: "42 Park Road, London",
    email: "ed.hellen@example.com",
  },
  {
    key: "5",
    name: "William Smith",
    salary: 27000,
    address: "62 Park Road, London",
    email: "william.smith@example.com",
  },
]);
</script>
<style scoped>
#adminUserPage .searchForm {
  margin-bottom: 20px;
}
</style>

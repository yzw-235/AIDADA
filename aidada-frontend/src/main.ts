import { createApp } from "vue";
import App from "./App.vue";
import ArcoVue from "@arco-design/web-vue";
import "@arco-design/web-vue/dist/arco.css";
import router from "./router";
import { createPinia } from "pinia";
//设置全局变量
const pinia = createPinia();
createApp(App).use(ArcoVue).use(pinia).use(router).mount("#app");

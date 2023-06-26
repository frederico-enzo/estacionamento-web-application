/* eslint-disable */
declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}
declare module 'vue-the-mask' {
  import { Plugin } from 'vue';

  const VueTheMask: Plugin<[]>;
  export default VueTheMask;
}
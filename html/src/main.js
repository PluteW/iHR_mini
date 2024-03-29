// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueBus from 'vue-bus'

import axios from 'axios'

import {
  Table,
  Tooltip,
  TableColumn,
  DatePicker,
  Pagination,
  Row,
  Select,
  Col,
  Button,
  ButtonGroup,
  Popover,
  Cascader,
  Badge,
  Dropdown,
  DropdownItem,
  DropdownMenu,
  Card,
  Option,
  OptionGroup,
  CheckboxGroup,
  Checkbox,
  Message,
  Menu,
  MenuItem,
  MenuItemGroup,
  Submenu,
  Tree,
  Tag,
  Radio,
  RadioGroup,
  Input,
  Form,
  FormItem,
  Dialog,
  Switch,
  MessageBox,
  Upload,
  TimePicker,
  Steps,
  Step,
  Collapse,
  CollapseItem,
  Notification,
  TabPane,
  Tabs
} from 'element-ui'
const components = [
  Row,
  Tooltip,
  Table,
  Select,
  Dropdown,
  DropdownMenu,
  Pagination,
  DropdownItem,
  TableColumn,
  DatePicker,
  Col,
  Button,
  ButtonGroup,
  Popover,
  Cascader,
  Badge,
  Card,
  Option,
  OptionGroup,
  CheckboxGroup,
  Checkbox,
  Message,
  Menu,
  MenuItem,
  MenuItemGroup,
  Submenu,
  Tree,
  Tag,
  Radio,
  RadioGroup,
  Input,
  Form,
  FormItem,
  Dialog,
  Switch,
  Upload,
  TimePicker,
  Steps,
  Step,
  Collapse,
  CollapseItem,
  TabPane,
  Tabs
]
components.forEach(element => {
  Vue.component(element.name, element)
})
// Vue.component(pdf, pdf)
Vue.prototype.$message = Message
Vue.prototype.$msgbox = MessageBox
Vue.prototype.$alert = MessageBox.alert
Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.baseURL = process.env.API_URL
Vue.prototype.$notify = Notification

axios.defaults.baseURL = 'http://localhost:8080'
Vue.config.productionTip = false
Vue.prototype.$addr = axios

Vue.use(VueBus)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

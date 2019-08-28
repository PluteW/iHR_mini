<template>
  <div>
    <li id = "head">
    <a>
      <img src="./assets/midea_pic.png" id = "midea_pic">
    </a>
    <a>
      <img :src="user.pic" alt="" id = "head_pic">
    </a>
    <a>
      <img src="static/images/menu.png" @click="openMenue" id = "menu_pic">
    </a>
    </li>
    <div id="app">
      <router-view/>
    </div>
  </div>
</template>

<script>
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
export default {
  name: 'App',
  data () {
    return {
      user: {
        id: '',
        pic: '',
        name: '',
        state: -1
      }
    }
  },
  created () { // 向后台请求用户数据
    const that = this
    if (localStorage.getItem('userdata') === '' || localStorage.getItem('userdata') === null || localStorage.getItem('userdata') === undefined) {
      that.user.pic = 'static/images/head_pic_unlog.png'
      that.user.state = 0
    } else {
      let data = localStorage.getItem('userdata')
      let userdata = JSON.parse(data)
      that.user.pic = 'static/images/hr_pics/' + userdata.picId
      that.user.id = userdata.id
      that.user.name = userdata.name
      that.user.state = userdata.state
    }
  },
  methods: {
    openMenue () {
      const that = this
      if (that.user.state === -1) { // 状态码默认为-1，未登录状态
        ElementUI.Message({
          message: '暂未登录',
          type: 'error'
        })
      } else if (that.user.state === 0) { // 状态码为0，账号正常，可转到我的页面
        window.location.href = '/hown'
      }
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
  margin-top: 30px;
}
#head{
  list-style: none
}#midea_pic{
  height: 40px;
  width: 85px;
  margin-left: 3%;
  margin-top: 2%;
}
#head_pic{
  width: 30px;
  height: 30px;
  margin-left: 78%;
  margin-top: 3%
}
#menu_pic{
  width: 30px;
  height: 30px;
  margin-left: 2%;
}
</style>

<template>
    <div id="app">
        <div id = "outer">
            <div id = 'continer'>
                <div id = "title">
                    登      录
                </div>
                <div class="form_title_name">
                    <a>账 号：</a>
                    <a><input id = "username" type="text" v-model="usernameValue"></a>
                </div>
                <div class="form_title_pass">
                    <a>密  码：</a>
                    <a><input id = "password" type="password" v-model="passwordValue"></a>
                </div>
                <span id = "errorMsg_span" v-show="errorMsg !== ''"></span>{{errorMsg}}
                <li style="list-style: none">
                    <el-button id = "btn_login" @click="login()">登录</el-button>
                    <el-button id = "btn_reset" @click="reset()">重置</el-button>
                </li>
            </div>
        </div>
    </div>
</template>
<script src="//unpkg.com/vue/dist/vue.js"></script>
<script src="//unpkg.com/element-ui@2.11.0/lib/index.js"></script>
<script>
import ElementUI, { Loading } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import Qs from "qs"
export default {
  name: 'App',
  data () {
      return{
          usernameValue: "",
          passwordValue: "",
          errorMsg:""
      }
  },
  methods: {
      login () {
        const _self = this;
        if (_self.usernameValue == "" || _self.passwordValue == "") { // 账号和密码任一个为空，报错
            _self.errorMsg = 'Please input valid username and password'
            return
        } // 提交账号密码，接收返回值
        let loadingInstance = Loading.service({
            text: '请稍等...',
            target: document.querySelector('.loadingtext')
        });
        axios(
            {
                method: 'post',
                url: 'ccs/',
                data: {
                    username: _self.usernameValue,
                    password: _self.passwordValue
                },
                transformRequest: [function (data) {
                var params = Qs.stringify(data, { arrayFormat: 'brackets' })
                return params;
                }],
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                timeout: 15000
            }
        ).then(
            function (res) {
                if (res.data) {
                    loadingInstance.close();
                    localStorage.setItem('userdata', res.data);
                    window.location.href = '/main' // 跳转到主页面
                }
            }, function (response) { // 如果返回错误，则提错
                if (response) {
                loadingInstance.close();
                _self.errorMsg = 'Please input valid username and password'
                Message({
                    type: 'warning',
                    message: '登录失败'
                })
                return
                }
            }
        )
      },
      reset () {
          let _self = this;
          _self.usernameValue = "",
          _self.passwordValue = "",
          _self.errorMsg = ''
      }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 40px;
  background: rgb(39, 146, 196);
  height: 100%;
  width: 100%;
}
#outer{
    height: 450px;
    width: 100%;
    padding-top: 70px;
}
#continer{
    text-align: center;
    background: white;
    height: 350px;
    width: 680px;
    margin: auto;
    border: 3px solid white;
    border-radius: 28px;
    padding-top: 50px;
}
#title{
    font-size: 34px;
    display: inline;
}
.form_title_name{
    font-size: 25px;
    width: 530px;
    height: 50px;
    margin: auto;
    margin-top: 50px;
}
.form_title_pass{
    font-size: 25px;
    width: 550px;
    height: 50px;
    margin: auto;
    margin-top: 30px;
}
#errorMsg_span{
    width: 12px;
    height: 12px;
    float: left;
    margin-right: 10px;
    margin-top: 5px;
}
#btn_login{
    margin-top: 6%;
    font-size: 22px;
    margin-left: 40%;
}
#btn_reset{
    font-size: 22px;
    margin-top: 6%;
    margin-left: 5%;
}
</style>

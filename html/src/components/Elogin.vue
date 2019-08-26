<template>
  <div id="app">
    <div id = "outer">
      <div id = 'continer'>
        <el-tabs v-model="activeName" @tab-click="handleClick" type="card" id = "label_continer" stretch>
          <el-tab-pane label="登 录" name="login" class="labels"></el-tab-pane>
            <div v-if="label_login">
              <div class="form_login_title">
                <a>账 号：</a>
                <a><input id = "username" type="text" v-model="usernameValue"></a>
              </div>
              <div class="form_login_title">
                <a>密  码：</a>
                <a><input id = "password" type="password" v-model="passwordValue"></a>
              </div>
              <span id = "errorMsg_span" v-show="errorMsg !== ''"></span>{{errorMsg}}
              <li style="list-style: none">
                <el-button id = "btn_login" @click="login">登录</el-button>
                <el-button id = "btn_reset" @click="login_reset">重置</el-button>
              </li>
          </div>
          <el-tab-pane label="注 册" name="register" class="labels"></el-tab-pane>
          <div v-if="lable_register">
              <div class="form_register_title">
                <a>账 号：</a>
                <a><input id = "username" type="text" v-model="usernameValue"></a>
              </div>
              <div class="form_register_title">
                <a>密  码：</a>
                <a><input id = "passwordOnce" type="password" v-model="passwordOnce"></a>
              </div>
              <div class="form_register_title">
                <a>确 认：</a>
                <a><input id = "passwordTwice" type="password" v-model="passwordTwice"></a>
              </div>
              <span id = "errorMsg_span" v-show="errorMsg !== ''"></span>{{errorMsg}}
              <li style="list-style: none">
                <el-button id = "btn_register" @click="register">注册</el-button>
                <el-button id = "btn_register_reset" @click="reginster_reset">重置</el-button>
              </li>
          </div>
        </el-tabs>
      </div>
    </div>
  </div>
</template>
<script src="//unpkg.com/vue/dist/vue.js"></script>
<script src="//unpkg.com/element-ui@2.11.0/lib/index.js"></script>
<script>
import ElementUI, { Loading, Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import Qs from "qs"
export default {
  name: 'App',
  data () {
    return{
      usernameValue: "",
      passwordValue: "",
      errorMsg:"",
      label_login: true,
      lable_register : false,
      activeName: 'label_login',
      passwordOnce: "",
      passwordTwice: ""
    }
  },
  mounted: function () {
    let loadingInstance = Loading.service({
          text: '请稍等...',
          target: document.querySelector('.loadingtext')
        });
    let userdata = localStorage.getItem('userdata')
    if (userdata !== '' && userdata !== undefined && userdata !== null){
      if (userdata.state === 1){
        window.location.href = '/emain'
      } else {
        loadingInstance.close()
      }
    } else {
      loadingInstance.close()
    }
  },
  methods: {
      handleClick (tab) {
        if (tab.name === 'login') {
          this.label_login = true
          this.lable_register = false
        } else if (tab.name === 'register') {
          this.usernameValue = ""
          this.label_login = false
          this.lable_register = true
        }
      },
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
            url: 'Elogin',  // 接收登录消息的地址
            data: {
              state: 1, // 状态码，1表示登录
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
            if (res.data.result) {
              loadingInstance.close();
              localStorage.setItem('userdata', res.data.userdata);
              window.location.href = '/emain' // 跳转到主页面
            } else {
              this.reset
              Message({
                type: 'warring',
                message: '登录信息错误'
              })
              loadingInstance.close();
            }
          }, function (response) { // 如果返回错误，则提错
            if (response) {
            loadingInstance.close();
            _self.errorMsg = 'Please input valid username and password';
            Message({
              type: 'warning',
              message: '登录失败'
            });
            return
            }
          }
        )
      },
      register(){
          const _self = this;
        if (_self.usernameValue == "" || _self.passwordOnce == ""|| _self.passwordTwice == "") { // 账号和密码任一个为空，报错
          _self.errorMsg = 'Please input username and password'
          return
        } // 提交账号密码，接收返回值
        if (_self.passwordOnce != _self.passwordTwice) {
          _self.errorMsg = 'Password Repead Wrong'
          return
        }
        let loadingInstance = Loading.service({
          text: '请稍等...',
          target: document.querySelector('.loadingtext')
        });
        axios(
          {
            method: 'post',
            url: 'register',  // 接收注册消息的地址
            data: {
              state: 1, // 状态码，2表示注册
              username: _self.usernameValue,
              password: _self.passwordOnce
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
              window.location.href = '/Emain' // 跳转到主页面
            }
          }, function (response) { // 如果返回错误，则提错
            if (response) {
            loadingInstance.close();
            _self.errorMsg = 'Unknowen Wrong,Try Again'
            Message({
              type: 'warning',
              message: '注册失败'
            })
            return
            }
          }
        )
      },
      login_reset () {
        let _self = this;
        _self.usernameValue = "";
        _self.passwordValue = "";
        _self.errorMsg = ''
      },
      reginster_reset () {
        let _self = this;
        _self.usernameValue = "";
        _self.passwordOnce = "";
        _self.passwordTwice = "",
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
#label_continer{
  /* height: 80px; */
  width: 80%;
  margin: auto;
  padding-top: 10px
}
.el-tabs_item{
  font-size: 34px !important ;
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
  padding-top: 30px;
}
#title{
  font-size: 34px;
  display: inline;
}
.form_login_title{
  font-size: 25px;
  width: 530px;
  height: 50px;
  margin: auto;
  margin-top: 40px;
}
.form_register_title{
  font-size: 25px;
  width: 530px;
  height: 50px;
  margin: auto;
  margin-top: 10px;
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
#btn_register{
  margin-top: 6%;
  font-size: 22px;
  margin-left: 40%;
}
#btn_register_reset{
  font-size: 22px;
  margin-top: 6%;
  margin-left: 5%;
}
</style>

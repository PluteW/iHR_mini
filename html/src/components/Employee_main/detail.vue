<template>
    <div>
        <div v-if="gotinfored">
          <el-button key="1" @click="addIntersested"
          style="margin: 2% 1% 2.5% 5%">收藏</el-button>
          <el-button key="2" @click="delet"
          style="margin: 2% 68% 2.5% 1%">删除</el-button>
          <el-button type="primary" key="3"  @click="send"
          style="margin: 2% 5% 2.5% 0.5%">投递</el-button>
        </div>
        <div v-if="gotinfored">
            <div>
              <div class="nameTitle">
                  岗  位：
              </div>
              <div class="name">
                {{jobDta.name}}
              </div>
              <div class="locationTitle">
                工作地点：
              </div>
              <div class="location">
                {{jobDta.location}}
              </div>
              <div class="statusTitle">
                岗位状态：
              </div>
              <div class="status">
                {{jobDta.status}}
              </div>
              <div class="salaryTitle">
                薪资待遇：
              </div>
              <div class="salary">
                {{jobDta.salary}}/月
              </div>
            </div>
        </div>
        <div v-if="!gotinfored">
            <img src="static/images/mideabear.jpg"  height="100%" width="100%" >
        </div>
    </div>
</template>

<script>
import { Loading, Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import Qs from 'qs'
// import filesaver from 'file-saver'
// var FileSaver = require('file-saver')
const jobDta = {
  name: 'IT开发工程师',
  location: '广州佛山',
  salary: '3000',
  status: '投递中'
}
export default {
  data () {
    return {
      gotinfored: false, // 默认没有获得数据，相关内容不显示
      jobId: localStorage.getItem('jobId'), // 获取jobid
      chooseType: 0, // 选项初始化 0 1收藏 2删除 3投递
      jobDta: jobDta // 表格初始化数据
    }
  },
  mounted: function () {
    let _self = this
    let loadingInstance = Loading.service({
      text: '请稍等...',
      target: document.querySelector('.loadingtext')
    })
    // 用$on事件来接收参数
    _self.$bus.on('jobId', (data) => {
    //   console.log(data)
      localStorage.setItem('jobId', data)
    })
    // 向后台请求岗位信息
    if (_self.jobId !== '' && _self.jobId !== null) { // 如果选中的jobId不为空，则向后台请求简历数据
      if (_self.gitinfor()) { // 尝试请求岗位信息
        _self.gotinfored = true
        Message({
          type: 'success',
          message: '数据获取成功！'
        })
      } else {
        Message({
          type: 'error',
          message: '数据获取失败！'
        })
      }
    } else {
      Message({
        type: 'info',
        message: '请先选择'
      })
    }
    loadingInstance.close()
  },
  methods: {
    gitinfor () { // 用于向后台请求简历数据
      let _self = this
      _self.chooseType = 0 // 代号置零，请求岗位数据
      axios(
        {
          method: 'post',
          url: 'ccs/', // 请求简历的地址
          data: {
            chooseType: _self.chooseType, // 选项的代号，
            jobId: _self.jobId // 此时的应聘者的代号
          },
          transformRequest: [function (data) {
            var params = Qs.stringify(data, { arrayFormat: 'brackets' })
            return params
          }],
          headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
          timeout: 15000
        }
      ).then(
        function (res) { // 如果返回数据，则放到表格中
          if (res.data) {
            _self.tableDta = res.data.tableDta // 获取工作数据地址
            return true
          }
        }, function (response) { // 如果返回错误，则报错
          if (response) {
            return false
          }
        }
      )
      return true
    },
    send () {
      let _self = this
      _self.chooseType = 3 // 3代表选择的是投递
      _self.handleChoose()
    },
    delet () {
      let _self = this
      _self.chooseType = 2 // 2代表选择的是删除
      _self.handleChoose()
    },
    addIntersested () {
      let _self = this
      _self.chooseType = 1 // 1代表选择的是收藏选项
      _self.handleChoose()
    },
    handleChoose () { // 推送和锁定的执行函数
      let _self = this
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      axios(
        {
          method: 'post',
          url: 'ccs/', // 对选中的岗位进行处理，提交请求
          data: {
            chooseType: _self.chooseType, // 选项的代号
            jobId: _self.jobId // 此时的岗位的代号
          },
          transformRequest: [function (data) {
            var params = Qs.stringify(data, { arrayFormat: 'brackets' })
            return params
          }],
          headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
          timeout: 15000
        }
      ).then(
        function (res) { // 如果返回数据，则放到表格中
          if (res.data) {
            loadingInstance.close()
            if (_self.chooseType === 2) {
              localStorage.removeItem('jobId')
            }
            Message({
              type: 'success',
              message: '操作成功'
            })
          }
        }, function (response) { // 如果返回错误，则提错
          if (response) {
            loadingInstance.close()
            Message({
              type: 'error',
              message: response
            })
          }
        }
      )
    }
  }
}
</script>
<style>
.nameTitle{
  margin-left: 30%;
  margin-top: 15px;
  font-size: 28px
}
.name{
  margin-left: 50%;
  margin-top: 10px;
  font-size: 24px
}
.locationTitle{
  margin-left: 30%;
  margin-top: 15px;
  font-size: 28px
}
.location{
  margin-left: 50%;
  margin-top: 10px;
  font-size: 24px
}
.statusTitle{
  margin-left: 30%;
  margin-top: 15px;
  font-size: 28px
}
.status{
  color: cornflowerblue;
  margin-left: 50%;
  margin-top: 10px;
  font-size: 24px
}
.salaryTitle{
  margin-left: 30%;
  margin-top: 15px;
  font-size: 28px
}
.salary{
  margin-left: 50%;
  margin-top: 10px;
  font-size: 24px
}
</style>

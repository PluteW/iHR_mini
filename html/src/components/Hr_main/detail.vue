<<<<<<< HEAD
<template>
    <div>
        <div v-if="gotResumed">
                <el-button type="primary" key="1"  @click="showAll"
                style="margin: 2% 60% 2.5% 0.5%">展开</el-button>
                <el-button type="primary" key="2"  @click="send" >推送</el-button>
                <el-button key="3" v-if="unlocked" @click="lock">锁定</el-button>
                <el-button key="4" v-if="!unlocked" @click="lock">解锁</el-button>
        </div>
        <div v-if="gotResumed">
            <pdf :src='pdfUrl' style="height: 180%;  width: 85%" ></pdf>
        </div>
        <div v-if="!gotResumed">
            <img src="static/images/mideabear.jpg"  height="100%" width="100%" >
        </div>
    </div>
</template>

<script>
import { Loading, Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import Qs from 'qs'
import pdf from 'vue-pdf'
import $ from 'jquery'
// import filesaver from 'file-saver'
// var FileSaver = require('file-saver')
export default {
  name: 'app',
  data () {
    return {
      gotResumed: false,
      employeeId: localStorage.getItem('employeeId'),
      chooseType: 0,
      unlocked: true, // 当前简历是否已经锁定
      pdfUrl: 'static/190822.pdf', // 展示简历用的数据
      currentPage: 0,
      pageCount: 0
    }
  },
  components: {
    pdf
  },
  mounted: function () {
    let _self = this
    let loadingInstance = Loading.service({
      text: '请稍等...',
      target: document.querySelector('.loadingtext')
    })
    // 用$on事件来接收参数
    _self.$bus.on('employeeId', (data) => {
    //   console.log(data)
      localStorage.setItem('employeeId', data)
    })
    // 向后台请求PDF文件
    if (_self.employeeId !== '' && _self.employeeId !== null) { // 如果选中的employeeId不为空，则向后台请求简历数据
      if (_self.getResume()) { // 尝试请求简历数据
        _self.gotResumed = true
        Message({
          type: 'success',
          message: '简历获取成功！'
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
    getResume () { // 用于向后台请求简历数据
      let _self = this
      _self.chooseType = 0 // 代号置零，请求简历数据
      axios(
        {
          method: 'post',
          url: 'ccs/', // 请求简历的地址
          data: {
            chooseType: _self.chooseType, // 选项的代号，
            employeeId: _self.employeeId // 此时的应聘者的代号
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
            _self.pdfUrl = res.data.pdfUrl // 获取简历数据地址
            _self.unlocked = res.data.unlocked // 获取简历状态
            return true
          }
        }, function (response) { // 如果返回错误，则报错
          if (response) {
            return false
          }
        }
      )
    },
    showAll () {
      let _self = this
      _self.chooseType = 1 // 1代表选择的是全部展开选项
      var $eleForm = $("<form method='get'></form>")
      $eleForm.attr('action', this.pdfUrl)
      $(document.body).append($eleForm)
      $eleForm.submit()
    },
    send () {
      let _self = this
      _self.chooseType = 2 // 2代表选择的是转送选项
      _self.handleChoose()
    },
    lock () {
      let _self = this
      _self.chooseType = 3 // 3代表选择的是锁定选项
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
          url: 'ccs/', // 对选中的简历文件进行处理，提交请求
          data: {
            chooseType: _self.chooseType, // 选项的代号
            employeeId: _self.employeeId // 此时的应聘者的代号
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
            if (_self.chooseType === 3 || _self.chooseType === 4) {
              _self.unlocked = !_self.unlocked
            }
            loadingInstance.close()
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
=======
<template>
    <div>
        <div v-if="gotResumed">
                <el-button type="primary" key="1"  @click="showAll"
                style="margin: 2% 60% 2.5% 0.5%">展开</el-button>
                <el-button type="primary" key="2"  @click="send" >推送</el-button>
                <el-button key="3" v-if="unlocked" @click="lock">锁定</el-button>
                <el-button key="4" v-if="!unlocked" @click="lock">解锁</el-button>
        </div>
        <div v-if="gotResumed">
            <pdf :src='pdfUrl' style="height: 180%;  width: 85%" ></pdf>
        </div>
        <div v-if="!gotResumed">
            <img src="static/images/mideabear.jpg"  height="100%" width="100%" >
        </div>
    </div>
</template>

<script>
import { Loading, Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import Qs from 'qs'
import pdf from 'vue-pdf'
import $ from 'jquery'
// import filesaver from 'file-saver'
// var FileSaver = require('file-saver')
export default {
  name: 'app',
  data () {
    return {
      gotResumed: false,
      employeeId: localStorage.getItem('employeeId'),
      chooseType: 0,
      unlocked: true, // 当前简历是否已经锁定
      pdfUrl: 'static/190822.pdf', // 展示简历用的数据
      currentPage: 0,
      pageCount: 0
    }
  },
  components: {
    pdf
  },
  mounted: function () {
    let _self = this
    let loadingInstance = Loading.service({
      text: '请稍等...',
      target: document.querySelector('.loadingtext')
    })
    // 用$on事件来接收参数
    _self.$bus.on('employeeId', (data) => {
    //   console.log(data)
      localStorage.setItem('employeeId', data)
    })
    // 向后台请求PDF文件
    if (_self.employeeId !== '' && _self.employeeId !== null) { // 如果选中的employeeId不为空，则向后台请求简历数据
      if (_self.getResume()) { // 尝试请求简历数据
        _self.gotResumed = true
        Message({
          type: 'success',
          message: '简历获取成功！'
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
    getResume () { // 用于向后台请求简历数据
      let _self = this
      _self.chooseType = 0 // 代号置零，请求简历数据
      axios(
        {
          method: 'post',
          url: 'ccs/', // 请求简历的地址
          data: {
            chooseType: _self.chooseType, // 选项的代号，
            employeeId: _self.employeeId // 此时的应聘者的代号
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
            _self.pdfUrl = res.data.pdfUrl // 获取简历数据地址
            _self.unlocked = res.data.unlocked // 获取简历状态
            return true
          }
        }, function (response) { // 如果返回错误，则报错
          if (response) {
            return false
          }
        }
      )
    },
    showAll () {
      let _self = this
      _self.chooseType = 1 // 1代表选择的是全部展开选项
      var $eleForm = $("<form method='get'></form>")
      $eleForm.attr('action', this.pdfUrl)
      $(document.body).append($eleForm)
      $eleForm.submit()
    },
    send () {
      let _self = this
      _self.chooseType = 2 // 2代表选择的是转送选项
      _self.handleChoose()
    },
    lock () {
      let _self = this
      _self.chooseType = 3 // 3代表选择的是锁定选项
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
          url: 'ccs/', // 对选中的简历文件进行处理，提交请求
          data: {
            chooseType: _self.chooseType, // 选项的代号
            employeeId: _self.employeeId // 此时的应聘者的代号
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
            if (_self.chooseType === 3 || _self.chooseType === 4) {
              _self.unlocked = !_self.unlocked
            }
            loadingInstance.close()
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
>>>>>>> 4e7324683b09f6eac8f7f1fe025057a545537c27

<template>
    <div>
        <div v-if="gotResumed" style="margin-left:8%">
                <el-button type="primary" key="1"  @click="showAll"
                style="margin: 2% 68% 2.5% 0.5%">展开</el-button>
                <el-button type="primary" key="2"  @click="send" >推送</el-button>
                <el-button key="3" v-if="unlocked" @click="lock">锁定</el-button>
                <el-button key="4" v-if="!unlocked" @click="lock">解锁</el-button>
        </div>
        <div v-if="gotResumed">
            <pdf :src='pdfUrl' style="height: 180%;  width: 85%;margin-left:7%" ></pdf>
        </div>
        <div v-if="!gotResumed" style="margin-left:1%">
            <img src="static/images/mideabear.jpg" width="98%" >
        </div>
        <el-dialog title="推送目标" :visible.sync="dialogVisible">
          <el-table align="center" style="width: 80%; margin:auto" border :data="tableData" @selection-change="handleSelectionChange" tooltip-effect="dark">
            <el-table-column type="selection" :reserve-selection="true"  width="55" align="center"></el-table-column>
            <el-table-column label="工号" align="center">
                <template slot-scope="scope" align="center">{{ scope.row.id }}</template>
            </el-table-column>
            <el-table-column label="姓名"  align="center">
                <template slot-scope="scope" align="center">{{ scope.row.name }}</template>
            </el-table-column>
            <el-table-column label="岗位"  align="center">
                <template slot-scope="scope" align="center">{{ scope.row.job }}</template>
            </el-table-column>
          </el-table>
          <div style="margin-top:3%;margin-left:60%">
            <el-button style="margin-right:10%" type="primary" @click="handleSend" > 确认</el-button>
            <el-button  @click="deletSend" > 取消</el-button>
          </div>
        </el-dialog>
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
      dialogVisible: false, // 是否显示弹窗，默认不显示
      gotResumed: false, // 判断是否已经获得了简历数据，默认为未获取到
      employeeId: localStorage.getItem('employeeId'), // 应聘编号
      chooseType: 0, // 选择的操作类型
      unlocked: true, // 当前简历是否已经锁定
      pdfUrl: '', // 展示简历用的数据
      tableData: [{ // 测试用假数据，推送目标
        id: '123456',
        name: '蒋小刀',
        job: '产品经理'
      }],
      multipleSelection: [], // 弹框列表中，选中行的人员id列表，默认未选中
      chooseId: [] // 选中的推送人员id
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
          url: '/hr/detail', // 请求简历的地址
          data: {
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
            _self.tableData = res.data.tableData // hr可选列表
            return true
          }
        }, function (response) { // 如果返回错误，则报错
          if (response) {
            Message({
              type: 'error',
              message: response
            })
            return false
          }
        }
      )
      return true
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
      _self.dialogVisible = true // 打开dialog选项框
    },
    handleSend () {
      let _self = this
      for (let item in _self.multipleSelection) {
        _self.chooseId.push(_self.multipleSelection[item]['id'])
      }
      _self.handleChoose() // 处理提交
      console.log(_self.chooseId)
      _self.dialogVisible = false // 选择框隐藏
    },
    deletSend () {
      let _self = this
      _self.dialogVisible = false // dialog选项框关闭
      _self.multipleSelection = [] // 选中框置零
      _self.chooseId = [] // 数据保存位置的选中id置空
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
      // 准备处理人员id的获取
      let data = localStorage.getItem('userdata')
      let userdata = JSON.parse(data)
      axios(
        {
          method: 'post',
          url: 'ccs/', // 对选中的简历文件进行处理，提交请求
          data: {
            chooseType: _self.chooseType, // 选项的代号
            employeeId: _self.employeeId, // 此时的应聘者的代号
            hrId: userdata.id, // 操作者id
            chooseId: JSON.stringify(_self.chooseId) // 保存提交目标的id
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
          Message({
            type: 'error',
            message: '后台处理错误'
          })
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
    },
    handleSelectionChange (val) { // 当弹出框的选择框发生更改后触发储存的改变
      this.multipleSelection = val // 这是一个数组 将选中的值进行重新赋值然后将里面的id进行保存
      console.log(this.multipleSelection)
    }
  }
}
</script>

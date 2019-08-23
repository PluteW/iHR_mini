<<<<<<< HEAD
<template>
    <div>
        <div style="margin: 40px 40px auto 40px">
            <el-form ref="form" :model="form" label-width="120px">
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="姓名：" label-width="90px" label-position = 'right'>
                            <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="性别：" label-width="90px" label-position = 'right'>
                            <el-select v-model="form.sex" placeholder="请选择性别" clearable>
                                <el-option key="0" label="女" :value="0"></el-option>
                                <el-option key="1" label="男" :value="1"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="应聘岗位：" label-width="100px" label-position = 'right'>
                            <el-select v-model="form.jobRegin" placeholder="请选择应聘岗位" clearable>
                                <el-option v-for="(item,index) in jobRegin" :key="index" :value="index" :label="item" ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="即时配送">
                            <el-switch v-model="form.delivery"></el-switch>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6" label-width="80px">
                        <el-form-item label="年龄：" label-position = 'right'>
                             <el-select v-model="form.age" placeholder="请选择年龄" clearable>
                                <el-option v-for="(item,index) in age" :key="index" :value="index" :label="item" ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="15">
                        <el-form-item label="学历水平：" label-position = 'right'>
                            <el-checkbox-group v-model="form.eduBackGround">
                                <el-checkbox label="全选" key = '0' :flagChecked="flagChecked" value = '0' @change="handleCheck"></el-checkbox>
                                <el-checkbox id="checkbox_1" label="本科在读"  key = '1' value = '1'></el-checkbox>
                                <el-checkbox id="checkbox_2" label="本科毕业" key = '2' value = '2'></el-checkbox>
                                <el-checkbox id="checkbox_3" label="研究生"  key = '3' value = '3'></el-checkbox>
                                <el-checkbox id="checkbox_4" label="博士"  key = '4' value = '4'></el-checkbox>
                                <el-checkbox id="checkbox_5" label="留学生" key = '5' value = '5'></el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="地域来源：" label-width="100px" label-position = 'right'>
                            <el-select v-model="form.region" placeholder="请选择地域来源" clearable>
                                <el-option v-for="(item,index) in region" :key="index"  :value="index" :label="item" ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="应聘时间：" label-width="100px" label-position = 'right'>
                            <el-select v-model="form.time" placeholder="请选择应聘时间" clearable>
                                <el-option v-for="(item,index) in time" :key="index" :value="index" :label="item" ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="简历状态：" label-width="100px" label-position = 'right'>
                            <el-select v-model="form.status" placeholder="请选择简历状态" clearable>
                                <el-option v-for="(item,index) in status" :key="index" :value="index" :label="item" ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item style="margin-left:60%">
                    <el-button type="primary" @click="onSubmit" style="margin-right: 7%">查询</el-button>
                    <el-button @click="reset">重置</el-button>
                </el-form-item>
                </el-form>
        </div>
        <div align="center">
            <el-table ref="multipleTable" style="width:90%" align="center" border :data="tableData" tooltip-effect="dark"  @row-click="handleView" @selection-change="handleTableSelection">
                <el-table-column type="selection" width="55" align="center">  </el-table-column>
                <el-table-column label="编号" width="120" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.id }}</template>
                </el-table-column>
                <el-table-column label="姓名" width="80" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.name }}</template>
                </el-table-column>
                <el-table-column label="性别" width="60" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.sex }}</template>
                </el-table-column>
                <el-table-column label="年龄" width="60" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.age }}</template>
                </el-table-column>
                <el-table-column label="应聘岗位" width="140" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.job }}</template>
                </el-table-column>
                <el-table-column label="学校" width="120" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.school }}</template>
                </el-table-column>
                <el-table-column label="专业" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.major }}</template>
                </el-table-column>
                <el-table-column label="学历" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.eduBack }}</template>
                </el-table-column>
                <el-table-column label="毕业时间" width="100" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.graduatedYear }}</template>
                </el-table-column>
                 <el-table-column prop="handle" label="操作" fixed="right" :show-overflow-tooltip="true" align="center" width="52">
                    <el-button type="text" aria-label="查看" style="min-width:auto" size="mini">查看</el-button>
                </el-table-column>
            </el-table>
            <el-row>
                <el-pagination  @current-change="setPageIndex" :current-page="pageIndex" :page-size="10"
                    layout="prev, pager, next" :total="resultTotal" :pager-count="5"
                    class="float-right"></el-pagination>
            </el-row>
        </div>
    </div>
</template>

<script>
import { Loading, Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import Qs from 'qs'
const eduBackGroundOptions = ['全选', '本科在读', '本科毕业', '研究生', '博士', '留学生']
// let loadingInstance = Loading.service({
//   text: '请稍等...',
//   target: document.querySelector('.loadingtext')
// })
export default {
  name: 'polling',
  props: ['cotent'],
  data () {
    return {
      message: this.cotent,
      form: {
        name: '', // 姓名
        region: '', // 应聘者来源
        sex: '', // 性别
        time: '', // 应聘时间
        jobRegin: '', // 应聘岗位
        status: '', // 简历状态
        eduBackGround: [], // 学历选择项
        age: '' // 年龄
      },
      pageIndex: 0, // 初始化页数
      resultTotal: 0, // 数据总数
      flagChecked: false, // 是否全选
      chileDhecked: false, // 用来控制子标签是否选中
      region: ['山东', '北京', '上海', '广州'], // 应聘者来源
      time: ['2019-8', '2019-9', '2019-7'], // 可供选择简历投递时间
      jobRegin: ['IT开发工程师', '应用工程师', '业务经理', '营销经理'], // 可供选择岗位
      status: ['待审核', '已审核', '流程中', '已锁定'], // 可供选择简历状态
      age: ['19', '20', '21', '22', '23'], // 可供选择的年龄范围
      tableData: [{ // 测试用数据
        id: '123456',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '789098',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '373704',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }]
    }
  },
  methods: {
    handleTableSelection (val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    handleCheck (val) {
      if (this.flagChecked) {
        this.form.eduBackGround = []
      } else {
        this.form.eduBackGround = eduBackGroundOptions
      }
      this.flagChecked = !this.flagChecked
    },
    handleView (row) {
      console.log(row.id)
      this.$bus.emit('employeeId', row.id)
      this.$emit('func', 'detail')
    },
    setPageIndex (val) {
      let _self = this
      _self.pageIndex = val
      _self.handleSubmit()
    },
    handleSubmit () {
      let _self = this
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      axios(
        {
          method: 'post',
          url: 'ccs/', // 请求数据地址
          data: {
            form: _self.form, // 保存了数据的对象
            pageIndex: _self.pageIndex
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
            _self.tableData = res.data.tableData
            _self.resultTotal = res.data.resultTotal
            Message({
              type: 'success',
              message: '查询成功'
            })
            loadingInstance.close()
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
      loadingInstance.close()
    },
    onSubmit () {
      console.log(this.form)
      let _self = this
      _self.pageIndex = 1
      this.handleSubmit()
    },
    reset () {
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      this.form.name = '' // 姓名
      this.form.region = [] // 应聘者来源
      this.form.sex = '' // 性别
      this.form.time = '' // 应聘时间
      this.form.jobRegin = '' // 应聘岗位
      this.form.status = '' // 简历状态
      this.form.eduBackGround = [] // 学历选择项
      this.form.age = ''
      this.flagChecked = false
      this.chileDhecked = false
      loadingInstance.close()
    }
  }
}
</script>
<style scoped>
.float-right {
    float: right;
    margin-left: 70%;
    margin-right: 8%;
    margin-top: 2%
}
</style>
=======
<template>
    <div>
        <div style="margin: 40px 40px auto 40px">
            <el-form ref="form" :model="form" label-width="120px">
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="姓名：" label-width="90px" label-position = 'right'>
                            <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="性别：" label-width="90px" label-position = 'right'>
                            <el-select v-model="form.sex" placeholder="请选择性别" clearable>
                                <el-option key="0" label="女" :value="0"></el-option>
                                <el-option key="1" label="男" :value="1"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="应聘岗位：" label-width="100px" label-position = 'right'>
                            <el-select v-model="form.jobRegin" placeholder="请选择应聘岗位" clearable>
                                <el-option v-for="(item,index) in jobRegin" :key="index" :value="index" :label="item" ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="即时配送">
                            <el-switch v-model="form.delivery"></el-switch>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6" label-width="80px">
                        <el-form-item label="年龄：" label-position = 'right'>
                             <el-select v-model="form.age" placeholder="请选择年龄" clearable>
                                <el-option v-for="(item,index) in age" :key="index" :value="index" :label="item" ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="15">
                        <el-form-item label="学历水平：" label-position = 'right'>
                            <el-checkbox-group v-model="form.eduBackGround">
                                <el-checkbox label="全选" key = '0' :flagChecked="flagChecked" value = '0' @change="handleCheck"></el-checkbox>
                                <el-checkbox id="checkbox_1" label="本科在读"  key = '1' value = '1'></el-checkbox>
                                <el-checkbox id="checkbox_2" label="本科毕业" key = '2' value = '2'></el-checkbox>
                                <el-checkbox id="checkbox_3" label="研究生"  key = '3' value = '3'></el-checkbox>
                                <el-checkbox id="checkbox_4" label="博士"  key = '4' value = '4'></el-checkbox>
                                <el-checkbox id="checkbox_5" label="留学生" key = '5' value = '5'></el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="地域来源：" label-width="100px" label-position = 'right'>
                            <el-select v-model="form.region" placeholder="请选择地域来源" clearable>
                                <el-option v-for="(item,index) in region" :key="index"  :value="index" :label="item" ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="应聘时间：" label-width="100px" label-position = 'right'>
                            <el-select v-model="form.time" placeholder="请选择应聘时间" clearable>
                                <el-option v-for="(item,index) in time" :key="index" :value="index" :label="item" ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="简历状态：" label-width="100px" label-position = 'right'>
                            <el-select v-model="form.status" placeholder="请选择简历状态" clearable>
                                <el-option v-for="(item,index) in status" :key="index" :value="index" :label="item" ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item style="margin-left:60%">
                    <el-button type="primary" @click="onSubmit" style="margin-right: 7%">查询</el-button>
                    <el-button @click="reset">重置</el-button>
                </el-form-item>
                </el-form>
        </div>
        <div align="center">
            <el-table ref="multipleTable" style="width:90%" align="center" border :data="tableData" tooltip-effect="dark"  @row-click="handleView" @selection-change="handleTableSelection">
                <el-table-column type="selection" width="55" align="center">  </el-table-column>
                <el-table-column label="编号" width="120" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.id }}</template>
                </el-table-column>
                <el-table-column label="姓名" width="80" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.name }}</template>
                </el-table-column>
                <el-table-column label="性别" width="60" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.sex }}</template>
                </el-table-column>
                <el-table-column label="年龄" width="60" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.age }}</template>
                </el-table-column>
                <el-table-column label="应聘岗位" width="140" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.job }}</template>
                </el-table-column>
                <el-table-column label="学校" width="120" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.school }}</template>
                </el-table-column>
                <el-table-column label="专业" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.major }}</template>
                </el-table-column>
                <el-table-column label="学历" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.eduBack }}</template>
                </el-table-column>
                <el-table-column label="毕业时间" width="100" align="center">
                    <template slot-scope="scope" align="center">{{ scope.row.graduatedYear }}</template>
                </el-table-column>
                 <el-table-column prop="handle" label="操作" fixed="right" :show-overflow-tooltip="true" align="center" width="52">
                    <el-button type="text" aria-label="查看" style="min-width:auto" size="mini">查看</el-button>
                </el-table-column>
            </el-table>
            <el-row>
                <el-pagination  @current-change="setPageIndex" :current-page="pageIndex" :page-size="10"
                    layout="prev, pager, next" :total="resultTotal" :pager-count="5"
                    class="float-right"></el-pagination>
            </el-row>
        </div>
    </div>
</template>

<script>
import { Loading, Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import Qs from 'qs'
const eduBackGroundOptions = ['全选', '本科在读', '本科毕业', '研究生', '博士', '留学生']
// let loadingInstance = Loading.service({
//   text: '请稍等...',
//   target: document.querySelector('.loadingtext')
// })
export default {
  name: 'polling',
  props: ['cotent'],
  data () {
    return {
      message: this.cotent,
      form: {
        name: '', // 姓名
        region: '', // 应聘者来源
        sex: '', // 性别
        time: '', // 应聘时间
        jobRegin: '', // 应聘岗位
        status: '', // 简历状态
        eduBackGround: [], // 学历选择项
        age: '' // 年龄
      },
      pageIndex: 0, // 初始化页数
      resultTotal: 0, // 数据总数
      flagChecked: false, // 是否全选
      chileDhecked: false, // 用来控制子标签是否选中
      region: ['山东', '北京', '上海', '广州'], // 应聘者来源
      time: ['2019-8', '2019-9', '2019-7'], // 可供选择简历投递时间
      jobRegin: ['IT开发工程师', '应用工程师', '业务经理', '营销经理'], // 可供选择岗位
      status: ['待审核', '已审核', '流程中', '已锁定'], // 可供选择简历状态
      age: ['19', '20', '21', '22', '23'], // 可供选择的年龄范围
      tableData: [{ // 测试用数据
        id: '123456',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '789098',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '373704',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }, {
        id: '037595',
        name: '王小虎',
        sex: '男',
        age: '24',
        job: 'IT开发工程师',
        school: '湖南大学',
        major: '化学',
        eduBack: '本科',
        graduatedYear: '2020'
      }]
    }
  },
  methods: {
    handleTableSelection (val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    handleCheck (val) {
      if (this.flagChecked) {
        this.form.eduBackGround = []
      } else {
        this.form.eduBackGround = eduBackGroundOptions
      }
      this.flagChecked = !this.flagChecked
    },
    handleView (row) {
      console.log(row.id)
      this.$bus.emit('employeeId', row.id)
      this.$emit('func', 'detail')
    },
    setPageIndex (val) {
      let _self = this
      _self.pageIndex = val
      _self.handleSubmit()
    },
    handleSubmit () {
      let _self = this
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      axios(
        {
          method: 'post',
          url: 'ccs/', // 请求数据地址
          data: {
            form: _self.form, // 保存了数据的对象
            pageIndex: _self.pageIndex
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
            _self.tableData = res.data.tableData
            _self.resultTotal = res.data.resultTotal
            Message({
              type: 'success',
              message: '查询成功'
            })
            loadingInstance.close()
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
      loadingInstance.close()
    },
    onSubmit () {
      console.log(this.form)
      let _self = this
      _self.pageIndex = 1
      this.handleSubmit()
    },
    reset () {
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      this.form.name = '' // 姓名
      this.form.region = [] // 应聘者来源
      this.form.sex = '' // 性别
      this.form.time = '' // 应聘时间
      this.form.jobRegin = '' // 应聘岗位
      this.form.status = '' // 简历状态
      this.form.eduBackGround = [] // 学历选择项
      this.form.age = ''
      this.flagChecked = false
      this.chileDhecked = false
      loadingInstance.close()
    }
  }
}
</script>
<style scoped>
.float-right {
    float: right;
    margin-left: 70%;
    margin-right: 8%;
    margin-top: 2%
}
</style>
>>>>>>> 4e7324683b09f6eac8f7f1fe025057a545537c27

//$(function() {
//	$('form').bootstrapValidator({
//		message: '验证不通过',
//		feedbackIcons: {
//	           valid: 'glyphicon glyphicon-ok',
//	           invalid: 'glyphicon glyphicon-remove',
//	           validating: 'glyphicon glyphicon-refresh'
//	       },
//            fields: {
//            	account: {
//                    message: '用户名验证失败',
//                    validators: {
//                        notEmpty: {
//                            message: '用户名不能为空'
//                        },
//                        stringLength: {
//                            min: 3,
//                            max: 18,
//                            message: '用户名长度必须在3到18位之间'
//                        },
//                    }
//                },
//                password: {
//                    message: '用户名验证失败',
//                    validators: {
//                        notEmpty: {
//                            message: '密码不能为空'
//                        }
//                    }
//                },
//                emails: {
//                    validators: {
//                        emailAddress: {
//                            message: '邮箱地址格式有误'
//                        }
//                    }
//                },
//                email: {
//                    validators: {
//                        notEmpty: {
//                            message: '邮箱地址不能为空'
//                        },
//                        emailAddress: {
//                            message: '邮箱地址格式有误'
//                        }
//                    }
//                },
//                phone:{
//                	validators: {
//                        regexp: {
//                            regexp: /^(\+86)?(13|15|17|18|)\d{11}(?!\d)$/,
//                            message: '手机格式不正确'
//                        }
//                	}
//                },
//                telphone:{
//                	validators: {
//                        notEmpty: {
//                            message: '手机不能为空'
//                        },
//                        regexp: {
//                            regexp: /^(\+86)?(13|15|17|18|)\d{11}(?!\d)$/,
//                            message: '手机格式不正确'
//                        }
//                	}
//                },
//                age:{
//                	validators: {
//                        regexp: {
//                            regexp: /^(1[3-9])|([2-8][0-9])|(90){1,2}$/,
//                            message: '年龄必须在13~90之间'
//                        }
//                	}
//                }
//            }
//      });
//});
//
//function checkAll(obj){
//    var bootstrapValidator = $(obj).data('bootstrapValidator');//获取表单对象
//    bootstrapValidator.validate(); //手动触发验证
//    var fail = bootstrapValidator.isValid();//获取表单的当前状态
//    return fail;
//}
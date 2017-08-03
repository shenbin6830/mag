/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	 config.language = 'zh-cn';
	 //config.uiColor = '#AADC6E';
	 config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;	 
	//字体默认名 plugins/font/plugin.js
	 config.font_defaultLabel='宋体';
	 //字体默认大小 plugins/font/plugin.js
	 config.fontSize_defaultLabel='14px';
	 config.extraPlugins += (config.extraPlugins?',lineheight':'lineheight'); //行距插件
	 config.filebrowserBrowseUrl = base+'/res/lib/ckfinder/ckfinder.html';
	 //config.filebrowserUploadUrl = '/filemanager/index.html'; 
	 config.filebrowserImageUploadUrl = base+'/res/lib/ckfinder/ckfinder.html?Type=Image';  
	 config.toolbar = 'teacher'; 
	 config.toolbar_teacher = [
	                    	['Source','Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker','Scayt'],
	                    	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	                    	'/',
	                    	['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	                    	['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','lineheight'],
	                    	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	                    	['Link','Unlink','Anchor'],
	                    	['Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	                    	'/',
	                    	['Styles','Format','Font','FontSize'],
	                    	['TextColor','BGColor']
	                    	]; 
	 config.toolbar_uploadFile = [['Link'],]; 
	 config.toolbar_Full = [
	['Source','-','Save','NewPage','Preview','-','Templates'],
	['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker','Scayt'],
	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button','ImageButton', 'HiddenField'],
	'/',
	['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	['Link','Unlink','Anchor'],
	['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	'/',
	['Styles','Format','Font','FontSize'],
	['TextColor','BGColor']
	]; 



};

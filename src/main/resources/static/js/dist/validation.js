/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/registerForm.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/registerForm.js":
/*!*****************************!*\
  !*** ./src/registerForm.js ***!
  \*****************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("function checkEveryThing() {\n  var firstNameTest = /^[a-zA-Z]+$/.test(firstName.value);\n  var lastNameTest = /^[a-zA-Z]+$/.test(lastName.value);\n  var usernameTest = /^[a-zA-Z0-9_]+$/.test(username.value);\n  var passwordTest = /^[a-zA-Z0-9_]+$/.test(password.value) && password.value.length >= 8 && password.value.length <= 15;\n  var passwordCheckTest = /^[a-zA-Z0-9_]+$/.test(passwordCheck.value) && passwordCheck.value.length >= 8 && passwordCheck.value.length <= 15 && password.value == passwordCheck.value; //summary test\n\n  if (firstNameTest && lastNameTest && usernameTest && passwordTest && passwordCheckTest) {\n    if (document.getElementById(\"register_button\") == null) document.getElementById(\"form\").insertAdjacentHTML('beforeend', submit);\n  } else {\n    var registerButton = document.getElementById(\"register_button\");\n    registerButton.parentNode.removeChild(registerButton);\n  }\n}\n\n;\n\nfunction passVerificationCheck() {\n  var passLengthCheck = passwordCheck.value.length >= 8 && passwordCheck.value.length <= 15;\n  if (/^[a-zA-Z0-9_]+$/.test(passwordCheck.value) && passLengthCheck && password.value == passwordCheck.value) passwordCheck.className = passwordCheck.className.replace(\" redBorder\", \"\");else if (!passwordCheck.classList.contains(\"redBorder\")) passwordCheck.className = passwordCheck.className + \" redBorder\";\n  checkEveryThing();\n} //loading document elements\n\n\nvar firstName = document.getElementById(\"firstName\");\nvar lastName = document.getElementById(\"lastName\");\nvar username = document.getElementById(\"username\");\nvar password = document.getElementById(\"password\");\nvar passwordCheck = document.getElementById(\"passwordCheck\"); //register Button\n\nvar submit = '<button id=\"register_button\" class=\"btn btn-lg btn-primary btn-block\">Register</button>';\nfirstName.addEventListener('input', function firstNameCheck() {\n  if (/^[a-zA-Z]+$/.test(firstName.value)) firstName.className = firstName.className.replace(\" redBorder\", \"\");else if (!firstName.classList.contains(\"redBorder\")) firstName.className = firstName.className + \" redBorder\";\n  checkEveryThing();\n});\nlastName.addEventListener('input', function lastNameCheck() {\n  if (/^[a-zA-Z]+$/.test(lastName.value)) lastName.className = lastName.className.replace(\" redBorder\", \"\");else if (!lastName.classList.contains(\"redBorder\")) lastName.className = lastName.className + \" redBorder\";\n  checkEveryThing();\n});\nusername.addEventListener('input', function usernameCheck() {\n  if (/^[a-zA-Z0-9_]+$/.test(username.value)) username.className = username.className.replace(\" redBorder\", \"\");else if (!username.classList.contains(\"redBorder\")) username.className = username.className + \" redBorder\";\n  checkEveryThing();\n});\npassword.addEventListener('input', function passCheck() {\n  var passLengthCheck = password.value.length >= 8 && password.value.length <= 15;\n  if (/^[a-zA-Z0-9_]+$/.test(password.value) && passLengthCheck) password.className = password.className.replace(\" redBorder\", \"\");else if (!password.classList.contains(\"redBorder\")) password.className = password.className + \" redBorder\";\n  passVerificationCheck();\n  checkEveryThing();\n});\npasswordCheck.addEventListener('input', function passVerification() {\n  passVerificationCheck();\n  checkEveryThing();\n});\n\n//# sourceURL=webpack:///./src/registerForm.js?");

/***/ })

/******/ });
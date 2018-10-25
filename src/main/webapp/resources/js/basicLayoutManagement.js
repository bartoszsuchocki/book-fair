/**
 * 
 */
function hideOrShowElement(id) {
	if (document.getElementById(id).style.display == "none") {
		document.getElementById(id).style.display = "block";
	} else {
		document.getElementById(id).style.display = "none";
	}
}
function scrollToElement(id, navigationId) {
	var element = document.getElementById(id);
	var navigation = document.getElementById(navigationId);
	var additionalOffset = 10;
	if (element == null || navigation == null) {
		return;
	}
	window.scrollTo(0, element.offsetTop - navigation.offsetHeight
			- additionalOffset);
}

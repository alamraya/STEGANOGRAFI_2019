"use strict";

var md5=function(){function r(){var d="",a;for(a=0;a<this.length;a++)d+=(this[a]>>4).toString(16)+(this[a]&15).toString(16);return d}var g,n=[],p=[];for(g=0;64>g;g++)n.push([738695,669989,770404,703814][g>>4]>>g%4*5&31),p.push(Math.floor(4294967296*Math.abs(Math.sin(1+g))));return function(d){var a=new Uint32Array(4),g=new Uint8Array(a.buffer),c,e,f,h,k,l,m,q,b;a[3]=271733878;a[0]=1732584193;a[1]=~a[3];a[2]=~a[0];if("string"===typeof d){e=[];for(b=f=0;b<d.length;b++)c=d.charCodeAt(b),128>c?e[f++]=c:(2048>c?e[f++]=c>>6|192:(55296==(c&64512)&&b+1<d.length&&56320==(d.charCodeAt(b+1)&64512)?(c=65536+((c&1023)<<10)+(d.charCodeAt(++b)&1023),e[f++]=c>>18|240,e[f++]=c>>12&63|128):e[f++]=c>>12|224,e[f++]=c>>6&63|128),e[f++]=c&63|128);d=Uint8Array.from(e)}c=new ArrayBuffer(64*Math.ceil((d.length+9)/64));b=new Uint8Array(c);c=new Uint32Array(c);b.set(d);b[d.length]=128;c[c.length-2]=8*d.length;for(m=0;m<c.length;m=m+=16){d=a[0];e=a[1];f=a[2];h=a[3];for(b=0;63>=b;b++)16>b?(k=e&f|~e&h,l=b):32>b?(k=e&h|f&~h,l=5*b+1):48>b?(k=e^f^h,l=3*b+5):(k=f^(e|~h),l=7*b),q=h,h=f,f=e,e+=d+k+p[b]+c[m+l%16]<<n[b]|d+k+p[b]+c[m+l%16]>>>32-n[b],d=q;a[0]+=d;a[1]+=e;a[2]+=f;a[3]+=h}g.hex=r;return g}}();

var strToUTF8Arr = function(sDOMStr) {
	var aBytes, nChr, nStrLen = sDOMStr.length,
		nArrLen = 0;

	/* mapping... */

	for (var nMapIdx = 0; nMapIdx < nStrLen; nMapIdx++) {
		nChr = sDOMStr.charCodeAt(nMapIdx);
		nArrLen += nChr < 0x80 ? 1 : nChr < 0x800 ? 2 : nChr < 0x10000 ? 3 : nChr < 0x200000 ? 4 : nChr < 0x4000000 ? 5 : 6;
	}

	aBytes = new Uint8Array(nArrLen);

	/* transcription... */

	for (var nIdx = 0, nChrIdx = 0; nIdx < nArrLen; nChrIdx++) {
		nChr = sDOMStr.charCodeAt(nChrIdx);
		if (nChr < 128) {
			/* one byte */
			aBytes[nIdx++] = nChr;
		} else if (nChr < 0x800) {
			/* two bytes */
			aBytes[nIdx++] = 192 + (nChr >>> 6);
			aBytes[nIdx++] = 128 + (nChr & 63);
		} else if (nChr < 0x10000) {
			/* three bytes */
			aBytes[nIdx++] = 224 + (nChr >>> 12);
			aBytes[nIdx++] = 128 + (nChr >>> 6 & 63);
			aBytes[nIdx++] = 128 + (nChr & 63);
		} else if (nChr < 0x200000) {
			/* four bytes */
			aBytes[nIdx++] = 240 + (nChr >>> 18);
			aBytes[nIdx++] = 128 + (nChr >>> 12 & 63);
			aBytes[nIdx++] = 128 + (nChr >>> 6 & 63);
			aBytes[nIdx++] = 128 + (nChr & 63);
		} else if (nChr < 0x4000000) {
			/* five bytes */
			aBytes[nIdx++] = 248 + (nChr >>> 24);
			aBytes[nIdx++] = 128 + (nChr >>> 18 & 63);
			aBytes[nIdx++] = 128 + (nChr >>> 12 & 63);
			aBytes[nIdx++] = 128 + (nChr >>> 6 & 63);
			aBytes[nIdx++] = 128 + (nChr & 63);
		} else /* if (nChr <= 0x7fffffff) */ {
			/* six bytes */
			aBytes[nIdx++] = 252 + /* (nChr >>> 32) is not possible in ECMAScript! So...: */ (nChr / 1073741824);
			aBytes[nIdx++] = 128 + (nChr >>> 24 & 63);
			aBytes[nIdx++] = 128 + (nChr >>> 18 & 63);
			aBytes[nIdx++] = 128 + (nChr >>> 12 & 63);
			aBytes[nIdx++] = 128 + (nChr >>> 6 & 63);
			aBytes[nIdx++] = 128 + (nChr & 63);
		}
	}
	return aBytes;
};
var method_image = null;
function what_method(data){
    method_image = data;
}

var container = null,
	embeddata = null,
	iv = [],
	stegger;

function initStegs(method) {
    if (method == 'embed') {
        console.time('stegger init');
        iv = strToUTF8Arr(document.querySelector('input[name="password"]').value);
        localStorage.password = document.querySelector('input[name="password"]').value;
        stegger = new f5stego(iv);
        console.timeEnd('stegger init');
    }else if (method == 'extract') {
        console.time('stegger init');
        iv = strToUTF8Arr(document.querySelector('input[name="password-ext"]').value);
        localStorage.password = document.querySelector('input[name="password-ext"]').value;
        stegger = new f5stego(iv);
        console.timeEnd('stegger init');
    }
}

var handleContainerSelect = function(evt) {
	container = null;

	var files = evt.target.files; // FileList object

	if (files[0] && files[0].type.match('image/jpeg.*')) {
		var reader = new FileReader();

		reader.onload = (function(theFile) {
			return function(e) {
				container = e.target.result;
			};
		})(files[0]);
        reader.readAsArrayBuffer(files[0]);
        if (method_image == 'embed') {
            document.getElementById('label-cover').innerHTML = files[0].name;
        }else{
            document.getElementById('label-stego').innerHTML = files[0].name;
        }
	} else {
		alert('Please select jpeg image!');
        if (method_image == 'embed') {
            document.querySelector('#file_cover').value = 'Cover Image';
        }else{
            document.querySelector('#stego-image').value = 'Stego Image';
        }
	}
};

var handleDataSelect = function(evt) {
	embeddata = null;

	var files = evt.target.files; // FileList object
	var reader = new FileReader();

	reader.onload = (function(theFile) {
		return function(e) {
			embeddata = e.target.result;
		};
	})(files[0]);

    reader.readAsArrayBuffer(files[0]);
    console.log(files[0]);
    document.getElementById('label-data').innerHTML = files[0].name;
};

var doEmbed = function() {
    initStegs('embed');
	if(!container){
		alert('Please select jpeg image!');
		return false;
	}

	if(!embeddata){
		alert('Please select file to embed!');
		return false;
	}

	var cover = new Uint8Array(container),
		data = new Uint8Array(embeddata),
		s, pck;

    console.time('embed');
    var start = new Date().getTime();
	try {
		stegger.parse(cover);

		stegger.clearAPPs();
		stegger.clearTail();

		s = stegger.f5put(data);

		pck = stegger.pack();
	} catch (e) {
		console.timeEnd('embed');
		console.error(e)
		alert('Embed fail!\n' + e);
		return false;
	}
    console.timeEnd('embed');
    var end = new Date().getTime();
    var time = end - start;

	console.dir(s);

	var dataHash = md5(data).hex();

	var blob = new Blob([pck], {type: "image/jpeg"});
    saveAs(blob, Date.now() + '.jpeg');
    console.log(dataHash);
    
	document.getElementById('output-embed').textContent = 'Ebedded data length: ' + data.length + ' bytes\n' +
		'Data md5 hash: ' + dataHash + '\n' +
		'Estimated image capacity: ' +s.stats.capacity[1] + ' bytes\n'+
		'Total bytes embedded: ' + s.embedded + ' (' +
		(100*s.embedded/s.stats.capacity[1]).toFixed(2) +'% of estimated max capacity)\n' +
		'Coefficients changed: ' + s.changed + ' (' + s.thrown + ' thrown)\n'+
        'Efficiency: ' + s.efficiency + ' bits per change with '+((1<<s.k)-1)+'/'+s.k+' code.\n' + 
        'Embed Time : ' + time + ' ms';
    
};

var doExtract = function() {
    initStegs('extract');
	if(!container){
		alert('Please select jpeg image!');
		return false;
	}

	var data, cover = new Uint8Array(container);

    console.time('extract');
    var start = new Date().getTime();
	try {
		stegger.parse(cover);

		data = stegger.f5get();
	} catch (e) {
		console.timeEnd('extract');
		console.error(e)
		alert('Extract fail!\n' + e);
		return false;
	}
    console.timeEnd('extract');
    var end = new Date().getTime();
    var time = end - start;
	var dataHash = md5(data).hex();

	if(!data || data.length === 0){
		alert('Nothing was extracted.');
		return false;
	}

	var blob = new Blob([data], {type: "application/octet-stream"});
	saveAs(blob, Date.now() + '.data');
    
	document.getElementById('output-extract').textContent = 'Extracted data length: ' + data.length + ' bytes\n' +
        'Data md5 hash: ' + dataHash + '\n' +
        'Extract Time : ' + time + ' ms';
    
};

var fr = null;
var byteCover = null;
var byteStego = null;
var coverWidth = null;
var coverHeight = null;
var coverSize = null;
var stegoWidth = null;
var stegoHeight = null;
var stegoSize = null;
    function oriImg() {
        var input = document.getElementById('ori-image');
        // var file = $("#objFile")[0].files[0];
        var file = input.files[0];
        fr = new FileReader();
        //fr.onload = receivedText;
        //fr.readAsText(file);
        fr.onload = (function(theFile) {
            return function(e) {
                byteCover = receivedText();
                console.log(byteCover);
                var img = new Image;
                img.src = fr.result;
                img.onload = function() {
                    coverSize = file.size;
                    coverWidth = img.width;
                    coverHeight = img.height;
                    console.log('Cover Image : '+coverWidth + 'x' + coverHeight); // image is loaded; sizes are available
                };
            };
        })(file);
        fr.readAsDataURL(file);
        //console.log(file);
        document.getElementById('label-ori').innerHTML = file.name;
        }
    function stegoImg() {
        var input = document.getElementById('stegoimg-file');
        // var file = $("#objFile")[0].files[0];
        var file = input.files[0];
        fr = new FileReader();
        fr.onload = (function(theFile) {
            return function(e) {
                byteStego = receivedText();
                console.log(byteStego);
                var img = new Image;
                img.src = fr.result;
                img.onload = function() {
                    stegoSize = file.size;
                    stegoWidth = img.width;
                    stegoHeight = img.height;
                    console.log('Stego Image : '+stegoWidth + 'x' + stegoHeight); // image is loaded; sizes are available
                };
            };
        })(file);
        //fr.readAsText(file);
        fr.readAsDataURL(file);
        document.getElementById('label-stegoimg').innerHTML = file.name;
        }

    function receivedText() {
        var b64Data = fr.result.split(',');
        var contentType = 'image/jpeg';
        //document.getElementById('editor').appendChild(document.createTextNode(fr.result))
        var byteCharacters = atob(b64Data[1]);
        var byteNumbers = Array.prototype.map.call(byteCharacters,
                                    charCodeFromCharacter);
        var uint8Data = new Uint8Array(byteNumbers);
        return b64toBlob(b64Data[1], contentType);
        //var blobUrl = URL.createObjectURL(blob);
    }

    function charCodeFromCharacter(c) {
        return c.charCodeAt(0);
    }

    function b64toBlob(b64Data, contentType, sliceSize) {
        contentType = contentType || '';
        sliceSize = sliceSize || 1024;
        var byteCharacters = atob(b64Data);
        var byteArrays = [];

        for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
            var slice = byteCharacters.slice(offset, offset + sliceSize);
            var byteNumbers = Array.prototype.map.call(slice, charCodeFromCharacter);
            var byteArray = new Uint8Array(byteNumbers);
            byteArrays.push(byteArray);
        }
        /*
        for (var i = 0; i < byteArray.length; i++) {
            alert(byteArray[i]);
        }
        */
       //console.log(byteArray);
       return byteArray;
    }

    function log10(value) {
        return Math.log(value) / Math.LN10;
    }

    var sumByte=0;
    function getPSNR(){
        var coverLength = byteCover.length;
        
        if ((coverHeight*coverWidth) == (stegoHeight*stegoWidth)) {
            var i = 0;
        
            while (i <= coverLength) {
                sumByte = bigInt(sumByte + Math.pow((bigInt(byteCover[i])-bigInt(byteStego[i])),2));
                i+=1;
            }
            
            var MSE = bigInt(sumByte)/(coverHeight*coverWidth);
            var PSNR = 10*log10(255*255/MSE);
            
            console.log(MSE);
            console.log(PSNR);

            document.getElementById('output-psnr').textContent = 
            'Cover Image size : ' + coverSize + ' bytes \n' +
            'Cover Image Resolution : ' + coverWidth + ' x ' + coverHeight + ' pixels\n' +
            'Stego Image size : ' + stegoSize + ' bytes \n' +
            'Stego Image Resolution : ' + stegoWidth + ' x ' + stegoHeight + ' pixels\n' +
            'Mean Square Error (MSE): ' + MSE + '\n' +
            'Peak Signal-to-noise Ratio (PSNR): ' + PSNR + ' dB';
        }else{
            document.getElementById('output-psnr').textContent = 
            'Cover Image size : ' + coverSize + ' bytes \n' +
            'Cover Image Resolution : ' + coverWidth + ' x ' + coverHeight + ' pixels\n' +
            'Stego Image size : ' + stegoSize + ' bytes \n' +
            'Stego Image Resolution : ' + stegoWidth + ' x ' + stegoHeight + ' pixels\n' +
            'Cover Image Resolution is not equal to Stego Image Resolution !';
        }
    }

document.getElementById('cover-image').onchange = handleContainerSelect;
document.getElementById('data-file').onchange = handleDataSelect;
document.getElementById('stego-image').onchange = handleContainerSelect;
document.getElementById('btn-embed').onclick = doEmbed;
document.getElementById('btn-extract').onclick = doExtract;

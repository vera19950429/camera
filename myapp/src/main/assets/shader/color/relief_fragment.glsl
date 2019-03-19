//precision mediump float;
//varying vec2 textureCoordinate;
//uniform sampler2D vTexture;
//uniform vec2 TexSize;
//  void main()
//  {
//     vec2 tex =v_texCoord;
//  	vec2 upLeftUV = vec2(tex.x-1.0/TexSize.x,tex.y-1.0/TexSize.y);
//  	vec4 curColor = texture2D(s_baseMap,v_texCoord);
//  	vec4 upLeftColor = texture2D(s_baseMap,upLeftUV);
//  	vec4 delColor = curColor - upLeftColor;
//  	float h = 0.3*delColor.x + 0.59*delColor.y + 0.11*delColor.z;
//    vec4 bkColor = vec4(0.5, 0.5, 0.5, 1.0);
//    gl_FragColor = vec4(h,h,h,0.0) +bkColor;
//}


//浮雕效果
precision mediump float;
varying vec2 textureCoordinate;
uniform sampler2D vTexture;
const highp vec3 W = vec3(0.2125, 0.7154, 0.0721);
const vec2 TexSize = vec2(100.0, 100.0);
const vec4 bkColor = vec4(0.5, 0.5, 0.5, 1.0);

void main()
{
    vec2 tex = textureCoordinate;
    vec2 upLeftUV = vec2(tex.x-1.0/TexSize.x, tex.y-1.0/TexSize.y);
    vec4 curColor = texture2D(vTexture, textureCoordinate);
    vec4 upLeftColor = texture2D(vTexture, upLeftUV);
    vec4 delColor = curColor - upLeftColor;
    float luminance = dot(delColor.rgb, W);
    gl_FragColor = vec4(vec3(luminance), 0.0) + bkColor;
}
//precision mediump float;
//varying vec2 textureCoordinate;
//uniform sampler2D vTexture;
//void main() {
//    vec4 color=texture2D( vTexture, textureCoordinate);
//    float rgb=color.g;
//    vec4 c=vec4(rgb,rgb,rgb,color.a);
//    gl_FragColor = c;
//}
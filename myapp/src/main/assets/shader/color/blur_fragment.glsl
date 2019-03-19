//precision mediump float;
//varying vec2 textureCoordinate;
//uniform sampler2D vTexture;
//void main() {
////    vec4 color=texture2D( vTexture, textureCoordinate);
////    float rgb=color.g;
////    vec4 c=vec4(rgb,rgb,rgb,color.a);
////    gl_FragColor = c;
////
////    float block=150.0;
////    float delta=1.0/block
////    vec4 color=vec4(0.0);
//    //权重
//}
precision mediump float;

varying vec2 textureCoordinate;
uniform sampler2D vTexture;
const vec2 TexSize = vec2(400.0, 400.0);
const vec2 mosaicSize = vec2(8.0, 8.0);

void main()
{
    vec2 intXY = vec2(textureCoordinate.x*TexSize.x, textureCoordinate.y*TexSize.y);
    vec2 XYMosaic = vec2(floor(intXY.x/mosaicSize.x)*mosaicSize.x, floor(intXY.y/mosaicSize.y)*mosaicSize.y);
    vec2 UVMosaic = vec2(XYMosaic.x/TexSize.x, XYMosaic.y/TexSize.y);
    vec4 color = texture2D(vTexture, UVMosaic);
    gl_FragColor = color;
}

precision mediump float;

const float PI = 3.14159265;
uniform sampler2D vTexture;

const float uD = 80.0; //旋转角度
const float uR = 0.5; //旋转半径

varying vec2 textureCoordinate;

void main()
{
    ivec2 ires = ivec2(512, 512);
    float Res = float(ires.s);

    vec2 st = textureCoordinate;
    float Radius = Res * uR;

    vec2 xy = Res * st;

    vec2 dxy = xy - vec2(Res/2., Res/2.);
    float r = length(dxy);

    float beta = atan(dxy.y, dxy.x) + radians(uD) * 2.0 * (-(r/Radius)*(r/Radius) + 1.0);//(1.0 - r/Radius);

    vec2 xy1 = xy;
    if(r<=Radius)
    {
        xy1 = Res/2. + r*vec2(cos(beta), sin(beta));
    }

    st = xy1/Res;

    vec3 irgb = texture2D(vTexture, st).rgb;

    gl_FragColor = vec4( irgb, 1.0 );
}


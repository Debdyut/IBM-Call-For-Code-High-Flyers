import * as React from 'react';
import { Svg, Path } from 'react-native-svg';

export const HomeIcon = (props) => {
  const height = props.height || 28;
  const width = props.width || 28;
  const fill = props.fill || '#000';
  const stroke = props.stroke || 'none';
  const strokeWidth = props.strokeWidth || 0;

  return (
    <Svg height={height} width={width} viewBox='0 0 32 32'>
      <Path fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
            d='M16.61,2.21a1,1,0,0,0-1.24,0L1,13.42,2.24,15,4,13.62V26a2,2,0,0,0,2,2H26a2,2,0,0,0,2-2V13.63L29.76,15,31,13.43ZM18,26H14V18h4Zm2,0h0V18a2,2,0,0,0-2-2H14a2,2,0,0,0-2,2v8H6V12.06L16,4.27l10,7.8V26Z'>
      </Path>
    </Svg>
  );
};

export const MapIcon = (props) => {
  const height = props.height || 28;
  const width = props.width || 28;
  const fill = props.fill || '#000';
  const stroke = props.stroke || 'none';
  const strokeWidth = props.strokeWidth || 0;

  return (
    <Svg height={height} width={width} viewBox='0 0 32 32'>
      <Path fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
            d='M16,10a3,3,0,1,1-3,3,3,3,0,0,1,3-3m0-2a5,5,0,1,0,5,5A5,5,0,0,0,16,8Z'>
      </Path>
      <Path fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
            d='M16,4a8.88,8.88,0,0,1,9,8.71,8.47,8.47,0,0,1-1.79,5.21l0,0,0,0L16,28.46,8.85,18l0,0,0,0A8.47,8.47,0,0,1,7,12.71,8.88,8.88,0,0,1,16,4m0-2A10.86,10.86,0,0,0,5,12.71a10.53,10.53,0,0,0,2.2,6.43L16,32l8.8-12.86A10.53,10.53,0,0,0,27,12.71,10.86,10.86,0,0,0,16,2Z'>
      </Path>
    </Svg>
  );
};

export const DonateIcon = (props) => {
  const height = props.height || 28;
  const width = props.width || 28;
  const fill = props.fill || '#000';
  const stroke = props.stroke || 'none';
  const strokeWidth = props.strokeWidth || 0;

  return (
    <Svg height={height} width={width} viewBox='0 0 32 32'>
      <Path fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
          d='M28.76,11.35A1,1,0,0,0,28,11H22V7a3,3,0,0,0-3-3H13a3,3,0,0,0-3,3v4H4a1,1,0,0,0-1,1.15L4.88,24.3a2,2,0,0,0,2,1.7H25.14a2,2,0,0,0,2-1.7L29,12.15A1,1,0,0,0,28.76,11.35ZM12,7a1,1,0,0,1,1-1h6a1,1,0,0,1,1,1v4H12ZM25.14,24H6.86L5.17,13H26.83Z'>
      </Path>
    </Svg>
  );
};

// export const ChatIcon = (props) => {
//   const height = props.height || 28;
//   const width = props.width || 28;
//   const fill = props.fill || '#000';
//   const stroke = props.stroke || 'none';
//   const strokeWidth = props.strokeWidth || 0;

//   return (
//     <Svg height={height} width={width} viewBox='0 0 32 32'>
//     <Path  fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
//       d="M468.53,306.575c-4.14-10.239-15.798-15.188-26.038-11.046c-10.241,4.14-15.187,15.797-11.047,26.038L455,379.833
// 				l-69.958-30.839c-5.064-2.232-10.827-2.267-15.917-0.095c-23.908,10.201-49.52,15.373-76.124,15.373
// 				c-107.073,0-179-83.835-179-162.136c0-89.402,80.299-162.136,179-162.136s179,72.734,179,162.136
// 				c0,6.975-0.65,15.327-1.781,22.913c-1.63,10.925,5.905,21.102,16.83,22.732c10.926,1.634,21.103-5.905,22.732-16.83
// 				c1.431-9.59,2.219-19.824,2.219-28.815c0-54.33-23.006-105.308-64.783-143.543C405.936,20.809,351.167,0,293.001,0
// 				S180.067,20.809,138.784,58.592c-37.332,34.168-59.66,78.516-63.991,126.335C27.836,216.023,0.001,265.852,0.001,319.525
// 				c0,33.528,10.563,65.34,30.67,92.717L1.459,484.504c-3.051,7.546-1.224,16.189,4.621,21.855
// 				c3.809,3.694,8.828,5.642,13.925,5.642c2.723-0.001,5.469-0.556,8.063-1.7l84.229-37.13c21.188,7.887,43.585,11.88,66.703,11.88
// 				c0.5,0,0.991-0.039,1.482-0.075c33.437-0.253,65.944-9.048,94.098-25.507c25.218-14.744,45.962-34.998,60.505-58.917
// 				c14.199-2.55,28.077-6.402,41.547-11.551l107.301,47.3c2.595,1.143,5.34,1.7,8.063,1.7c5.097-0.001,10.117-1.949,13.926-5.642
// 				c5.845-5.666,7.672-14.308,4.621-21.855L468.53,306.575z M179.002,445c-0.273,0-0.539,0.03-0.81,0.041
// 				c-20.422-0.104-40.078-4.118-58.435-11.95c-5.089-2.173-10.852-2.138-15.916,0.095l-46.837,20.646l15.109-37.375
// 				c2.793-6.909,1.512-14.799-3.322-20.47c-18.835-22.097-28.79-48.536-28.79-76.462c0-31.961,13.445-62.244,36.969-85.206
// 				c7.324,39.925,27.989,78.117,59.162,108.119c38.791,37.333,90.101,58.961,145.506,61.565
// 				C255.626,429.608,218.402,445,179.002,445z"/>
// 			<Circle cx="292.001" cy="203" r="20"/>
// 			<Circle cx="372.001" cy="203" r="20"/>
// 			<Circle cx="212.001" cy="203" r="20"/>      
//     </Svg>
//   );
// };

export const ChatIcon = (props) => {
  const height = props.height || 28;
  const width = props.width || 28;
  const fill = props.fill || '#000';
  const stroke = props.stroke || 'none';
  const strokeWidth = props.strokeWidth || 0;

  return (
    <Svg height={height} width={width} viewBox='0 0 32 32'>
      <Path fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
          d='M17.74,30,16,29l4-7h6a2,2,0,0,0,2-2V8a2,2,0,0,0-2-2H6A2,2,0,0,0,4,8V20a2,2,0,0,0,2,2h9v2H6a4,4,0,0,1-4-4V8A4,4,0,0,1,6,4H26a4,4,0,0,1,4,4V20a4,4,0,0,1-4,4H21.16Z'>
      </Path>
      <Path fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
          d='M8 10H24V12H8zM8 16H18V18H8z'>
      </Path>
    </Svg>
  );
};

export const SearchIcon = (props) => {
  const height = props.height || 28;
  const width = props.width || 28;
  const fill = props.fill || '#000';
  const stroke = props.stroke || 'none';
  const strokeWidth = props.strokeWidth || 0;

  return (
    <Svg height={height} width={width} viewBox='0 0 32 32'>
      <Path fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
          d='M30,28.59,22.45,21A11,11,0,1,0,21,22.45L28.59,30ZM5,14a9,9,0,1,1,9,9A9,9,0,0,1,5,14Z'>
      </Path>
    </Svg>
  );
};

export const CheckedIcon = (props) => {
  const height = props.height || 28;
  const width = props.width || 28;
  const fill = props.fill || '#000';
  const stroke = props.stroke || 'none';
  const strokeWidth = props.strokeWidth || 0;

  return (
    <Svg height={height} width={width} viewBox='0 0 32 32'>
      <Path fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
          d='M26,4H6A2,2,0,0,0,4,6V26a2,2,0,0,0,2,2H26a2,2,0,0,0,2-2V6A2,2,0,0,0,26,4ZM6,26V6H26V26Z'>
      </Path>
      <Path fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
          d='M14 21.5L9 16.54 10.59 15 14 18.35 21.41 11 23 12.58 14 21.5z'>
      </Path>
    </Svg>
  );
};

export const UncheckedIcon = (props) => {
  const height = props.height || 28;
  const width = props.width || 28;
  const fill = props.fill || '#000';
  const stroke = props.stroke || 'none';
  const strokeWidth = props.strokeWidth || 0;

  return (
    <Svg height={height} width={width} viewBox='0 0 32 32'>
      <Path fill={fill} stroke={stroke} strokeLinecap='round' strokeLinejoin='round' strokeWidth={strokeWidth}
          d='M26,4H6A2,2,0,0,0,4,6V26a2,2,0,0,0,2,2H26a2,2,0,0,0,2-2V6A2,2,0,0,0,26,4ZM6,26V6H26V26Z'>
      </Path>
    </Svg>
  );
};
